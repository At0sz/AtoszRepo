package Gyakorlas2;

import java.util.*;
import java.util.stream.Collectors;

public class gyakorlas2_1 {
    static void main() {
        List<String> devices = Arrays.asList("Server-192.168.1.10", "PC-192.168.1.50",
                "Server-10.0.0.1", "Printer-192.168.1.100", "Server-172.16.0.5");

        List<String> serverList = devices.stream()
                .filter(p -> p.contains("Server"))
                .map(p -> p.replace("Server-", ""))
                .toList();

        System.out.println(serverList);
        System.out.println();
        List<Integer> speeds = Arrays.asList(10, 50, 100, 10, 20, 100, 500, 1000);

        var speedsList = speeds.stream()
                .filter(p -> p > 50)
                .sorted()
                .distinct()
                .toList();

        System.out.println(speedsList);
        System.out.println();
        List<Integer> latencies = Arrays.asList(15, 22, 10, 45, 12, 18, 50, 8);

        OptionalDouble average = latencies.stream()
                .mapToInt(p -> (int) p)
                .average();

        int minlatency = latencies.stream().mapToInt(p -> p).min().orElse(0);

        System.out.println(average);
        System.out.println(minlatency);
        System.out.println();

        List<String> rawData = Arrays.asList("192.168.1.1", "10.0.0.5", "", "192.168.1.1", "invalid_ip", "172.16.0.100", " ", "10.0.0.5");

        var cleanList2 = rawData.stream()
                .map(p -> p.trim())
                .filter(p -> !p.isEmpty())
                .filter(p -> p.contains("."))
                .distinct()
                .sorted()
                .toList();

        var cleanList2String = cleanList2.stream()
                .collect(Collectors.joining(", "));


        System.out.println(cleanList2);
        System.out.println();
        System.out.println("Felsorolás: ");
        cleanList2.forEach(System.out::println);
        System.out.println();
        System.out.println(cleanList2String);

        List<Device> network = Arrays.asList(
                new Device("R1", "Router", 250),
                new Device("S1", "Switch", 10),
                new Device("SRV-DB", "Server", 400),
                new Device("R2", "Router", 5),
                new Device("SRV-WEB", "Server", 600)
        );

        var networkList = network.stream()
                .filter(p -> !p.getType().equals("Switch"))
                .filter(p -> p.getUptime() > 100)
                .map(p -> p.getName())
                .toList();
        System.out.println();
        networkList.forEach(System.out::println);

        int[] numbers = {12, 45, 67, 2, 9, 120};
        int maxNumber = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > maxNumber) {
                maxNumber = numbers[i];
            }
        }
        ;
        System.out.println();
        System.out.println(maxNumber);
        System.out.println();

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                System.out.println(numbers[i]);
            }
        }
        System.out.println();

        double sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        System.out.println("Átlag:" + (sum / numbers.length));
        System.out.println();


        try {
            String input = "123a";
            int number = Integer.parseInt(input);
            System.out.println(number * 2);
        } catch (Exception e) {
            System.out.println("Érvénytelen formátum! " + e.getMessage());

        }


    }
}

class Device {
    String name;
    String type; // "Router", "Switch", "Server"
    int uptime;  // napokban

    Device(String name, String type, int uptime) {
        this.name = name;
        this.type = type;
        this.uptime = uptime;
    }

    // Getterek a Stream-hez
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getUptime() {
        return uptime;
    }
}