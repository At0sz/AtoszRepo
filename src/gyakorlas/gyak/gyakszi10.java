package gyakorlas.gyak;

import java.util.*;
import java.util.stream.Collectors;

public class gyakszi10 {
    public static void main(String[] args) {

        List<AuditLog> scanResults = Arrays.asList(
                new AuditLog("192.168.1.1", "Budapest", Arrays.asList()),
                new AuditLog("192.168.1.50", "Budapest", Arrays.asList("TIMEOUT", "AUTH_FAIL")),
                new AuditLog("10.0.0.5", "Debrecen", Arrays.asList("DISK_FULL")),
                new AuditLog("10.0.0.10", "Budapest", Arrays.asList("TIMEOUT")),
                new AuditLog("172.16.0.100", "Szeged", Arrays.asList("AUTH_FAIL", "TIMEOUT", "OVERHEAT"))
        );

        List<String> errorReport = scanResults.stream()
                .filter(p -> p.errors.size() >= 2)
                .map(p -> p.ip)
                .toList();

        try {
            java.nio.file.Files.write(java.nio.file.Paths.get("critical_report.txt"), errorReport);
            System.out.println("Fájl elmentve!");
        } catch (Exception e) {
            System.out.println("Hiba a mentésnél.");
        }

        Map<String, Long> errorStats = scanResults.stream()
                .collect(Collectors.groupingBy(
                        log -> log.location, // Csoportosítás alapja: Város
                        Collectors.summingLong(log -> log.errors.size()) // Mit adjunk össze? A hibák számát!
                ));

        System.out.println("Helyszíni hiba-statisztika: " + errorStats);


        List<String> rawLogs = Arrays.asList("192.168.1.1", "10.0.0.5", "192.168.1.20");

        List<String> formattedLogs = rawLogs.stream()
                .map(p -> "Belső háló: " + p)
                .toList();
        // 1. Használd a .map()-et, hogy minden String elé odaírd a szöveget!
        // Tipp: p -> "Belső háló: " + p
        // 2. Zárd le egy .toList()-el!

        List<String> belsosIPk = rawLogs.stream()
                .filter(p -> p.startsWith("192"))
                .toList();

        System.out.println("\nBelsosIPk: \n" + belsosIPk);
        System.out.println("\n");
        System.out.println(formattedLogs);


        List<String> pingTimes = Arrays.asList("45", "120", "30", "150", "60");

        List<Integer> highLatency = pingTimes.stream()
                .map(p -> Integer.parseInt(p) * 2) // Átalakítás számmá ÉS matek
                .filter(val -> val > 100)          // Szűrés a már átalakított számon
                .toList();

        System.out.println(highLatency);

        List<String> incomingIPs = Arrays.asList("192.168.1.1", "10.0.0.5", "172.16.0.100", "192.168.1.50");
        List<String> blackList = Arrays.asList("10.0.0.5", "172.16.0.100");

        List<String> checkedList = incomingIPs.stream()
                .map(p -> blackList.contains(p) ? p + " [DENIED]" : p + " [OK]")
                .toList();
        System.out.println("\n\n");
        System.out.println(checkedList);

        List<String> finalReport = incomingIPs.stream()
                .filter(p -> p.startsWith("192"))
                .map(p -> blackList.contains(p) ? p + " [DENIED]" : p + " [OK]")
                .sorted()
                .toList();

        System.out.println("\n\n");
        System.out.println(finalReport);

        List<NetworkLog> logs = Arrays.asList(
                new NetworkLog("192.168.1.1", "Budapest", 500),
                new NetworkLog("192.168.1.5", "Budapest", 1200),
                new NetworkLog("10.0.0.1", "Debrecen", 2500),
                new NetworkLog("172.16.0.10", "Szeged", 300),
                new NetworkLog("192.168.1.10", "Budapest", 100),
                new NetworkLog("10.0.0.8", "Debrecen", 800)
        );

        Map<String, List<NetworkLog>> moreThan400 = logs.stream()
                .filter(p -> p.dataMB > 400)
                .collect(Collectors.groupingBy(p -> p.city));


        System.out.println("\n\n");
        moreThan400.forEach((city, deviceList) -> {
            System.out.println(city + " forgalmas eszközei: " + deviceList);
        });


        Map<String, Long> deviceCountByCity = logs.stream()
                .filter(p -> p.dataMB > 400)
                .collect(Collectors.groupingBy(
                        p -> p.city,
                        Collectors.counting() // Ez megszámolja az elemeket a listák helyett
                ));

        System.out.println("Városonkénti darabszám: " + deviceCountByCity);


        List<NetworkDevice> inventory = Arrays.asList(
                new NetworkDevice("Archer C7", "TP-Link", 150.5),
                new NetworkDevice("EdgeRouter", "Ubiquiti", 500.0),
                new NetworkDevice("Catalyst", "Cisco", 1200.0),
                new NetworkDevice("Deco M4", "TP-Link", 50.2),
                new NetworkDevice("UniFi AP", "Ubiquiti", 300.0),
                new NetworkDevice("Nexus", "Cisco", 2500.0)
        );

        Map<String, Double> collectionByVendors = inventory.stream()
                .collect(Collectors.groupingBy(p -> p.vendor, Collectors.summingDouble(p -> p.trafficGB)));

        System.out.println("\n\n");
        System.out.println(collectionByVendors);

        List<DeviceInfo> devices = Arrays.asList(
                new DeviceInfo("192.168.1.1", "Cisco-2960"),
                new DeviceInfo("10.0.0.5", "MikroTik-v3"),
                new DeviceInfo("172.16.0.10", "Juniper-EX")
        );

// Ez a "külső" adatbázisunk a jogosultságokról
        Map<String, String> securityLevels = Map.of(
                "192.168.1.1", "HIGH",
                "10.0.0.5", "MEDIUM",
                "172.16.0.10", "LOW"
        );

        List<String> enrichedReport = devices.stream()
                .map(d -> d.model + " -> Security: " + securityLevels.get(d.ip))
                .toList();

        System.out.println("\n\n");
        System.out.println(enrichedReport);


    }
}

class DeviceInfo {
    String ip;
    String model;

    DeviceInfo(String ip, String model) {
        this.ip = ip;
        this.model = model;
    }
}

class AuditLog {
    String ip;
    String location;
    List<String> errors; // pl. "TIMEOUT", "AUTH_FAIL", "DISK_FULL"

    AuditLog(String ip, String location, List<String> errors) {
        this.ip = ip;
        this.location = location;
        this.errors = errors;
    }
}

class NetworkLog {
    String ip;
    String city;
    int dataMB;

    NetworkLog(String ip, String city, int dataMB) {
        this.ip = ip;
        this.city = city;
        this.dataMB = dataMB;
    }

    @Override
    public String toString() {
        return ip + " (" + dataMB + " MB)";
    }
}


class NetworkDevice {
    String model;
    String vendor;
    double trafficGB;

    NetworkDevice(String model, String vendor, double trafficGB) {
        this.model = model;
        this.vendor = vendor;
        this.trafficGB = trafficGB;
    }
}

