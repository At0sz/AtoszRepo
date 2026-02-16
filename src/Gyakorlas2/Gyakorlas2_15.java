package Gyakorlas2;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gyakorlas2_15 {
    public static void main(String[] args) {
        System.out.println();
        // Nyers adatok - Figyeld a hibás sort a végén!
        List<String> rawData = Arrays.asList(
                "192.168.1.1:80:ALLOW",
                "10.0.0.5:22:DENY",
                "172.16.0.1:443:ALLOW",
                "10.0.0.5:445:DENY",
                "8.8.8.8:ERROR:DENY" // <--- Ez itt el fogja törni a parseInt-et!
        );

        List<LogEntry15> logs = new ArrayList<>();

        // 1. FELADAT: Hibakezelés (Try-Catch) az importálásnál
        for (String row : rawData) {
            try {
                String[] p = row.split(":");
                logs.add(new LogEntry15(p[0], Integer.parseInt(p[1]), p[2]));
            } catch (NumberFormatException e) {
                // Ha a port nem szám, ide ugrik a kód, és nem omlik össze az egész program
                System.out.println("❌ Hibás sor átugorva: " + row);
            }
        }

        System.out.println("\n--- STATISZTIKA (Stream API) ---");

        // 2. FELADAT: Számlálás Stream-mel
        long denyCount = logs.stream()
                .filter(l -> l.getAction().equals("DENY"))
                .count();
        System.out.println("Blokkolt kérések összesen: " + denyCount);

        // 3. FELADAT: Keresés (Csoportosítás IP szerint)
        System.out.println("\n--- KERESÉS: 10.0.0.5 eseményei ---");
        logs.stream()
                .filter(l -> l.getIp().equals("10.0.0.5"))
                .forEach(System.out::println);
    }
}

@Data
@AllArgsConstructor
class LogEntry15 {
    private String ip;
    private int port;
    private String action; // ALLOW / DENY
}