package gyakorlas.gyak;

import java.util.*;
import java.util.stream.*;

// A recordot az osztályon kívülre rakjuk
record Traffic2(String ip, int port, int size) {
}

record NetworkDevice3(String name, String brand, String ip, String status) {
}

public class gyakszi15 {
    public static void main(String[] args) {
        List<String> rawData12 = Arrays.asList(
                "192.168.1.10;80;1500",
                "10.0.0.5;22;200",
                "192.168.1.10;443;3000",
                "172.16.0.1;23;50",    // TILTOTT PORT (Telnet)
                "10.0.0.5;22;150",
                "192.168.1.20;3389;4500", // TILTOTT PORT (RDP)
                "172.16.0.1;21;120"     // TILTOTT PORT (FTP)
        );

        List<Integer> blackList = Arrays.asList(21, 23, 3389);

        // A Stream folyamat
        Map<String, Integer> blockedStats = rawData12.stream()
                .map(line -> {
                    String[] p = line.split(";");
                    return new Traffic2(p[0], Integer.parseInt(p[1]), Integer.parseInt(p[2]));
                })
                .filter(t -> blackList.contains(t.port())) // Csak a tiltott portok maradnak
                .collect(Collectors.groupingBy(
                        Traffic2::ip, // Kulcs az IP lesz
                        Collectors.summingInt(Traffic2::size) // Az érték pedig a forgalmak összege
                ));

        blockedStats.forEach((ip, sum) -> {
            String status = (sum > 1000) ? "!!! KRITIKUS TAMADAS !!!" : "Gyanus forgalom";
            System.out.printf("%s -> Összesen: %d byte | Állapot: %s\n", ip, sum, status);
        });

        List<String> deviceLogs = Arrays.asList(
                "Router-Main;Cisco;192.168.1.1;Active",
                "Switch-Floor1;TP-Link;192.168.1.10;Inactive",
                "AP-Guest;Ubiquiti;10.0.0.5;Active",
                "Router-Backup;Cisco;192.168.1.2;Active",
                "Switch-Storage;D-Link;192.168.1.20;Active"
        );

        // Egyetlen Stream lánc az egész!
        Map<String, List<NetworkDevice3>> brandMap = deviceLogs.stream()
                .map(line -> {
                    String[] p = line.split(";");
                    return new NetworkDevice3(p[0], p[1], p[2], p[3]);
                })
                .filter(d -> d.status().equals("Active"))
                .collect(Collectors.groupingBy(NetworkDevice3::brand));

        // Bónusz: Szép kiíratás
        brandMap.forEach((brand, devices) -> {
            System.out.println(brand.toUpperCase() + " eszközök száma: " + devices.size());
            devices.forEach(d -> System.out.println("  - " + d.name() + " [" + d.ip() + "]"));
        });

    }
}