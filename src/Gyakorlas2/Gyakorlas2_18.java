package Gyakorlas2;

import java.util.*;
import java.util.stream.Collectors;

public class Gyakorlas2_18 {

        public static void main(String[] args) {
            List<String> rawLogs = generateLogs(20);

            // 1. Egyedi IP-k
            Set<String> uniqueIps = getUniqueIps(rawLogs);

            // 2. Port statisztika (SUCCESS próbálkozások)
            Map<Integer, Long> portStats = getSuccessStatsByPort(rawLogs);

            // 3. Hibás IP-k listázása
            List<String> failedIps = getFailedIps(rawLogs);

            // Kiíratások...
            System.out.println("---Port Statisztika (SUCCES) ---");
            portStats.forEach((port, stats) -> {System.out.println(portStats.get(port) + " " + stats);});
            System.out.println("\n--- Hibás ip cím lista ---");
            failedIps.forEach(System.out::println);
        }
        // Itt jönnek a metódusaid...

    public static List<String> getFailedIps(List<String> rawLogs) {
        return rawLogs.stream()
                .filter(log -> log.endsWith("FAILED"))
                .map(log -> log.split(":")[0]) // Csak az IP kell!
                .distinct()                    // Hogy egy IP ne szerepeljen többször
                .sorted()
                .toList();                     // Itt jön vissza a kész lista
    }

    public static Map<Integer, Long> getSuccessStatsByPort(List<String> rawLogs) {
        return rawLogs.stream()
                // 1. Csak a sikereseket tartsuk meg
                .filter(log -> log.endsWith("SUCCESS"))
                // 2. Csoportosítunk Port szerint
                .collect(Collectors.groupingBy(
                        log -> Integer.parseInt(log.split(":")[1]), // Kulcs: a Port
                        Collectors.counting()                      // Érték: hány darab van benne
                ));
    }

    public static Set<String> getUniqueIps(List<String> rawLogs) {
            Set<String> uniqueIps = new HashSet<>();
            for (String rawLog : rawLogs) {
                String[] logs = rawLog.split(":");
                uniqueIps.add(logs[0]);
            }
            return uniqueIps;

    }

    public static List<String> generateLogs(int count) {
        List<String> logs = new ArrayList<>();
        Random rand = new Random();

        // Előre definiált adatok a "merítéshez"
        String[] ips = {"192.168.1.1", "10.0.0.15", "172.16.0.5", "192.168.1.20"};
        int[] ports = {80, 443, 8080};
        String[] statuses = {"SUCCESS", "FAILED"};

        for (int i = 0; i < count; i++) {
            // Válasszunk random indexeket
            String ip = ips[rand.nextInt(ips.length)];
            int port = ports[rand.nextInt(ports.length)];
            String status = statuses[rand.nextInt(statuses.length)];

            // Itt jön az "összegyúrás"
            String logEntry = ip + ":" + port + ":" + status;
            logs.add(logEntry);
        }
        return logs;
    }




    }


