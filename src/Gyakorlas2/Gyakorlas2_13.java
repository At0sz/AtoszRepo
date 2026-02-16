package Gyakorlas2;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gyakorlas2_13 {
    public static void main(String[] args) {

        String[] rawLogs = {"ERROR: Timeout on 192.168.1.1",
                "INFO: Connection established 10.0.0.5",
                "WARNING: Packet loss 192.168.1.20",
                "ERROR: Auth fail 192.168.1.1",
                "INFO: Update success 172.16.0.5"

        };

        List<String> logList = new ArrayList<>(Arrays.asList(rawLogs));


        System.out.println("--- LOG ELEMZÉS INDUL ---");

        // 3. FELADAT: Szűrés és számlálás
        long errorCount = logList.stream()
                .filter(p -> p.contains("ERROR"))
                .count();

        logList.forEach(p -> {
            if (p.contains("ERROR")) {

                System.out.println(p);
            }
            if (p.contains("192.168")) {
                System.out.println("Belső hálozati esemény: " + p);
            }
        });

        System.out.println("\nHibák száma: " + errorCount+"\n");

        List<EntryLog> entryLogs = logList.stream()
                .map(p -> {
                    String[] parts = p.split(" ");
                    String level = parts[0].replace(":", ""); // "ERROR:" -> "ERROR"

                    // Biztonságosabb darabolás: Az IP az utolsó elem
                    String ip = parts[parts.length - 1];

                    // A köztes rész az üzenet (összefűzve a maradékot)
                    StringBuilder messageBuilder = new StringBuilder();
                    for (int i = 1; i < parts.length - 1; i++) {
                        messageBuilder.append(parts[i]).append(" ");
                    }
                    String message = messageBuilder.toString().trim();

                    return new EntryLog(level, message, ip);
                })
                .toList();


        entryLogs.forEach(System.out::println);


    }
}

@Data

@AllArgsConstructor
class EntryLog {
    String level;
    String message;
    String ip;

}
