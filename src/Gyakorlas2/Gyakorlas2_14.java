package Gyakorlas2;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class Gyakorlas2_14 {
    public static void main(String[] args) {


//        // 1. Hozz létre egy ArrayList-et a logoknak!
//        List<String> logs = new ArrayList<>();
//
//
//        logs.add("192.168.1.1:80:ALLOW");
//        logs.add("10.0.0.5:22:DENY");
//        logs.add("192.168.1.100:443:ALLOW");
//        logs.add("8.8.8.8:53:ALLOW");
//        logs.add("10.0.0.5:22:DENY");
//        logs.add("192.168.1.1:8080:DENY");
//        // 2. Add hozzá a fenti sorokat (amiket már meg-wrapeltél)!
//
//
//        System.out.println("\n=== HÁLÓZATI ANALÍZIS ===");
//
//        int blockedCount = 0;
//        List<String> suspiciousIps = new ArrayList<>();
//
//        // 3. CIKLUS FELADAT:
//        for (String entry : logs) {
//            String[] split = entry.split(":");
//            if(entry.contains("DENY")){
//                blockedCount++;
//
//                suspiciousIps.add(split[0]);
//            }else if(split[1].equals("22")){
//                System.out.println("Figyelem SSH probálkozás!");
//            }
//            // A) Ha a sorban szerepel a "DENY" szó:
//            //    - Növeld a blockedCount-ot!
//            //    - Szemezd ki az IP-t (az első kettőspontig tartó rész)!
//            //    - Add hozzá a suspiciousIps listához!
//
//            // B) Ha a port ":22" (SSH), írd ki: "FIGYELEM: SSH próbálkozás!"
//        }
//
//        System.out.println("Blokkolt kisérletek száma: " + blockedCount);
//        suspiciousIps.forEach(System.out::println);
//        // 4. ÖSSZEGZÉS:
//        // Írasd ki, hány blokkolt kísérlet volt!
//        // Írasd ki az egyedi gyanús IP-ket! (Itt jöhet a String Manipulation: 'Remove Duplicates')

        System.out.println();

        List<String> rawData = List.of(
                "192.168.1.1:80:ALLOW",
                "10.0.0.5:22:DENY",
                "192.168.1.100:443:ALLOW"
        );

        // Ez az igazi objektumokat tároló lista
        List<LogEntry14> logs = new ArrayList<>();

        // 1. FELADAT: Alakítsd át a Stringeket objektumokká!
        for (String row : rawData) {
            String[] parts = row.split(":");
            // Itt a mágia: példányosítunk egy LogEntry-t a darabokból
            // A portnál használd az Integer.parseInt(parts[1]) -t!
            logs.add(new LogEntry14(parts[0], Integer.parseInt(parts[1]), parts[2]));
        }

        // 2. FELADAT: Most már objektumokkal dolgozunk!
        // Írasd ki azokat a logokat, amiknek a portja 100 felett van!
        for (LogEntry14 log : logs) {
            if (log.getPort() > 100) {
                // Itt látod a Lombok erejét: van .getPort() és .getIp() metódusod!
                System.out.println("Magas port észlelve: " + log.getIp() + " -> " + log.getPort());
            }
        }

        System.out.println();

        logs.stream()
                .filter(log-> log.getPort() > 100)
                .forEach(log -> System.out.println(log.getIp() + " -> " + log.getPort() + " -> " + log.getAction()));


    }
}

@Data
@AllArgsConstructor
class LogEntry14 {
    private String ip;
    private int port;
    private String action; // ALLOW / DENY
}
