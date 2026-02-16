package gyakorlas.gyak;

import java.util.*;
import java.util.stream.*;


public class gyakszi16 {
    static void main(String[] args) {

        List<String> rawIps = Arrays.asList(
                "192.168.1.1",
                "10.0.0.256",    // HIBÁS (256)
                "172.16.0.1",
                "127.0.0",       // HIBÁS (csak 3 rész)
                "8.8.8.8",
                "10.0.1.500"     // HIBÁS (500)
        );

        Map<Boolean, List<String>> ipReport = rawIps.stream()
                .collect(Collectors.partitioningBy(ip -> {
                    String[] parts = ip.split("\\.");
                    if (parts.length != 4) return false;
                    try {
                        for (String part : parts) {
                            int value = Integer.parseInt(part);
                            if (value < 0 || value > 255) return false;
                        }
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }));

        System.out.println("✅ Valid IP címek: " + ipReport.get(true));
        System.out.println("❌ Hibás IP címek: " + ipReport.get(false));


        List<String> events = Arrays.asList(
                "192.168.1.1;PING;SUCCESS;10",
                "10.0.0.5;HTTP;ERROR;500",
                "192.168.1.1;PING;SUCCESS;12",
                "172.16.0.10;SSH;SUCCESS;45",
                "10.0.0.5;HTTP;ERROR;404",
                "192.168.1.1;PING;ERROR;0",
                "172.16.0.10;SSH;SUCCESS;38"
        );
        ///Formátum: IP ; Protokoll ; Státusz ; Válaszidő (ms)

        Map<String, List<NetworkEvent>> eventsList = events.stream()
                .map(p -> {
                    String[] parts = p.split(";");
                    return new NetworkEvent(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
                })
                .filter(p -> !p.proto().equals("PING") && p.status().equals("ERROR"))
                .collect(Collectors.groupingBy(NetworkEvent::ip));

        eventsList.forEach((k, l) -> {
            System.out.println(k);
            l.forEach(System.out::println);

        });

        List<String> traffic = Arrays.asList(
                "User_A:150",
                "User_B:80",
                "User_C:250",
                "User_D:400",
                "User_E:20",
                "User_F:320"
        );

        List<String[]> topTraffic = traffic.stream()
                .map(p -> p.split(":"))
                .filter(p -> Integer.parseInt(p[1]) > 100)
                // Itt az 'a' és 'b' tömböket hasonlítjuk össze a 1. indexük alapján
                .sorted((a, b) -> Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1])))
                .limit(3)
                .toList();

        topTraffic.forEach(p -> System.out.println(p[0] + " -> " + p[1] + " Mbps"));

        List<Integer> ports = Arrays.asList(80, 443, 21, 22, 23, 25, 443, 80, 53);


    }
}

record NetworkEvent(String ip, String proto, String status, int time) {
}