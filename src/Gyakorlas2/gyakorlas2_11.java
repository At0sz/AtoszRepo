package Gyakorlas2;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

public class gyakorlas2_11 {
    public static void main(String[] args) {

        String allow = "ALLOW";
        String block = "BLOCK";
        List<Log2> firewallLogs = Arrays.asList(
                new Log2("192.168.1.50", 80, allow, 1500),
                new Log2("10.0.0.15", 22, block, 64),
                new Log2("192.168.1.50", 443, allow, 2500),
                null, // Valami hiba a naplózásban
                new Log2("172.16.0.1", 22, block, 64),
                new Log2("10.0.0.15", 22, block, 128),
                new Log2("192.168.1.100", 80, allow, 3000),
                new Log2("172.16.0.1", 22, block, 64)
        );

        var cleanList = firewallLogs.stream()
                .filter(Objects::nonNull)
                .filter(p -> p.getAction().equals(block))
                .collect(Collectors.groupingBy(
                        Log2::getSourceIp,
                        Collectors.summingInt(Log2::getPacketSize)
                ));

        System.out.println("\n---Öszesitett lista---");
        System.out.printf("%-20s | %10s%n", "IP CÍM", "FORGALOM");
        System.out.println("------------------------------------------");
        cleanList.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(p ->
                        System.out.printf("%-20s | %10d B%n", p.getKey(), p.getValue())
                );

        System.out.println("\nA legtöbb kisérlet ezen a porton történt");
        cleanList.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(p -> System.out.printf("%-10s | %2d B%n", p.getKey(), p.getValue()));


    }
}

@Data
@AllArgsConstructor
class Log2 {
    String sourceIp;
    int port;
    String action;
    int packetSize;

}


