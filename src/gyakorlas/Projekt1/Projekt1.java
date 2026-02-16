package gyakorlas.Projekt1;

import java.util.*;
import java.util.stream.Collectors;

public class Projekt1 {
    public static void main(String[] args) {

        List<String> logs = Arrays.asList(
                "192.168.1.10;DESKTOP-PC;ALLOW;450",
                "192.168.1.15;SERVER-01;ALLOW;12500",
                "10.0.0.5;EXTERNAL-IP;BLOCK;0",
                "192.168.1.10;DESKTOP-PC;ALLOW;320",
                "192.168.1.20;PRINTER;ALLOW;15",
                "INVALID_LOG_ENTRY",
                "10.0.0.7;MALWARE-SITE;BLOCK;0",
                "192.168.1.15;SERVER-01;ALLOW;8900"
        );

        Map<String, List<TrafficLog>> deviceMap = logs.stream()
                .filter(line -> line.contains(";")) // 1. Csak a jó sorok
                .map(line -> {
                    String[] p = line.split(";");
                    // 2. Itt gyártsd le az új TrafficLog objektumot!
                    return new TrafficLog(p[0], p[1], p[2], Integer.parseInt(p[3]));
                })
                .collect(Collectors.groupingBy(log -> log.ip));

        int totalAllowTraffic = deviceMap.values().stream()
                .flatMap(List::stream) // Itt "lapítjuk ki" a listák listáját egy sima TrafficLog folyammá
                .filter(log -> log.status.equals("ALLOW"))
                .mapToInt(log -> log.traffic)
                .sum();

        TrafficLog biggestTraffic = deviceMap.values().stream()
                .flatMap(List::stream) // Kilapítjuk a listákat
                .max(Comparator.comparingInt(log -> log.traffic)) // Megkeresi a legnagyobbat a forgalom alapján
                .orElse(null); // Ha üres lenne a lista, null-t ad vissza

        List<String> blockedMachines = deviceMap.values().stream()
                .flatMap(List::stream)
                .filter(log -> log.status.equals("BLOCK"))
                .map(log -> log.name) // Csak a gépnév kell
                .distinct()           // Duplikációk ki
                .sorted()             // ABC sorrendbe
                .toList();

        System.out.println("Összesített forgalom: " + totalAllowTraffic + " MB");
        System.out.println("Legnagyobb forgalmú eszköz: " + (biggestTraffic != null ? biggestTraffic.name : "Nincs adat"));
        System.out.println("Blokkolt gépek (ABC): " + blockedMachines);
    }
}


class TrafficLog {
    String ip;
    String name;
    String status;
    int traffic;

    TrafficLog(String ip, String name, String status, int traffic) {
        this.ip = ip;
        this.name = name;
        this.status = status;
        this.traffic = traffic;
    }


}