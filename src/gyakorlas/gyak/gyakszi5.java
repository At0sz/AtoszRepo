package gyakorlas.gyak;

import java.util.*;
import java.util.stream.Collectors;

public class gyakszi5 {
    static void main(String[] args) {

        List<String> rawAssets = Arrays.asList(
                "BP-01;Router;Cisco;192.168.1.1;ACTIVE",
                "VIE-05;Switch;HP;10.0.0.55;ACTIVE",
                "LDN-02;Server;Dell;172.16.5.10;MAINTENANCE",
                "BP-04;Switch;Cisco;192.168.1.10;ACTIVE",
                "INVALID_ENTRY_123",
                "VIE-01;Router;MikroTik;10.0.0.1;ACTIVE",
                "BP-02;Server;HP;192.168.1.20;OFFLINE"
        );

        Map<String, List<NetworkAsset>> assets = rawAssets.stream()
                .filter(line -> line.contains(";"))
                .map(line -> {
                    String[] p = line.split(";");
                    String location = line.split("-")[0];
                    return new NetworkAsset(location, p[1], p[2], p[3], p[4]);
                })
                .collect(Collectors.groupingBy(line -> line.manufacturer));


        List<String> activeAssets = assets.values().stream()
                .flatMap(List::stream)
                .filter(p -> p.status.equals("ACTIVE"))
                .map(p -> p.ip)
                .distinct()
                .sorted().
                toList();

        Long activeBp = assets.values().stream()
                .flatMap(List::stream)
                .filter(p -> p.status.equals("ACTIVE"))
                .filter(p -> p.location.equals("BP"))
                .distinct()
                .count();

        String firstActiveCisco = assets.values().stream()
                .flatMap(List::stream)
                .filter(p -> p.manufacturer.equals("Cisco")) // Itt szűrsz a gyártóra
                .filter(p -> p.status.equals("ACTIVE")) // Itt az állapotra
                .map(p -> p.ip) // Csak az IP (String) kell nekünk
                .findFirst() // Megkeresi a legelsőt, ami átment a szűrőkön
                .orElse("Nincs találat");

        List<NetworkAsset> activeDevices = assets.values().stream()
                .flatMap(List::stream)
                .filter(p -> p.status.equals("ACTIVE"))
                .sorted(Comparator.comparing(p -> p.ip)) // Itt mondod meg, mi a kulcs a rendezéshez
                .toList();


        System.out.println("--- STATISZTIKA ---");
        System.out.println("Aktív eszközök Budapesten: " + activeBp + " db");
        System.out.println("Aktív IP címek: " + activeAssets);
        System.out.println("Első talált aktív Cisco IP: " + firstActiveCisco);
        System.out.println("Az aktív eszközök listája:");
        activeDevices.forEach(device -> System.out.println(" - " + device));


        try {
            // 1. Átalakítjuk az objektum listánkat egy String listává
            List<String> reportLines = activeDevices.stream()
                    .map(device -> "Eszköz: " + device.toString())
                    .toList();

            // 2. Kiírjuk a fájlba (fájlnév, adat, és hogy mi legyen a végén)
            java.nio.file.Files.write(
                    java.nio.file.Paths.get("C:/Users/Atosz/Desktop/network_report.txt"),
                    reportLines
            );

            System.out.println("\n✅ A riport sikeresen elkészült: network_report.txt");

        } catch (java.io.IOException e) {
            System.out.println("❌ Hiba történt a fájl mentésekor: " + e.getMessage());
        }

        System.out.println("A program itt dolgozik: " + System.getProperty("user.dir"));

    }
}

class NetworkAsset {
    String location;

    String type;
    String manufacturer;
    String ip;
    String status;

    NetworkAsset(String location, String type, String manufacturer, String ip, String status) {
        this.location = location;
        this.type = type;
        this.manufacturer = manufacturer;
        this.ip = ip;
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (%s)", location, manufacturer, type, ip);
    }
}
