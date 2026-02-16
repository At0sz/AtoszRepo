package Gyakorlas2;

import java.util.*;
import java.util.stream.*;

public class gyakorlas2_4 {
    public static void main(String[] args) {

        List<LogEntry> logList = Arrays.asList(
                new LogEntry("192.168.1.1", 80, true),
                new LogEntry("10.0.0.5", 22, false),
                new LogEntry("10.0.0.5", 22, false),
                new LogEntry("10.0.0.5", 22, false),
                new LogEntry("192.168.1.1", 443, true),
                new LogEntry("172.16.0.10", 22, false),
                new LogEntry("10.0.0.5", 22, false)
        );

        logList.stream()
                .filter(p -> !p.isSuccess)
                .map(p -> "🚫 RIASZTÁS: Sikertelen belépés a [" + p.port + "] porton! Forrás: " + p.ipAddress)
                .distinct()
                .forEach(System.out::println);
        System.out.println();
        Map<String, Long> attackStats = logList.stream()
                .collect(Collectors.groupingBy(
                        p -> p.ipAddress,
                        Collectors.counting()
                ));

        attackStats.forEach((ip, count) -> {
            if (count > 2) System.out.println("⚠️ KRITIKUS: " + ip + " már " + count + " alkalommal próbálkozott!");
        });
    }
}


class LogEntry {
    String ipAddress;
    int port;
    boolean isSuccess;

    public LogEntry(String ipAddress, int port, boolean isSuccess) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.isSuccess = isSuccess;
    }
}