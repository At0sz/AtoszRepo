package gyakorlas.gyak;

import java.util.*;
import java.util.stream.Collectors;

public class gyakszi7 {
    static void main(String[] args) {

        List<String> authLogs = Arrays.asList(
                "192.168.1.10;admin;SUCCESS",
                "192.168.1.12;user1;SUCCESS",
                "192.168.1.10;admin;FAIL",
                "172.16.0.5;guest;FAIL",
                "192.168.1.10;admin;FAIL",
                "172.16.0.5;guest;FAIL",
                "192.168.1.15;user2;SUCCESS",
                "172.16.0.5;guest;FAIL"
        );

        Map<String, List<AuthLog>> authMap = authLogs.stream()
                .filter(p -> p.contains(";"))
                .map(p -> {
                    String[] split = p.split(";");
                    return new AuthLog(split[0], split[1], split[2]);
                })
                .collect(Collectors.groupingBy(p -> p.ip));

        authMap.entrySet().stream()
                .filter(entry -> entry.getValue().stream()
                        .filter(log -> log.status.equals("FAIL"))
                        .count() >= 3)
                .forEach(entry -> {
                    // Kigyűjtjük a gyanús neveket erről az IP-ről (csak a FAIL-ek közül)
                    List<String> triedUsers = entry.getValue().stream()
                            .filter(log -> log.status.equals("FAIL"))
                            .map(log -> log.username)
                            .distinct()
                            .toList();

                    System.out.println("🚨 RIASZTÁS: Gyanús IP detektálva: " + entry.getKey());
                    System.out.println("   Próbálkozások száma: " + entry.getValue().size());
                    System.out.println("   Célba vett felhasználók: " + triedUsers);
                    System.out.println("--------------------------------------");
                });


    }
}


class AuthLog {
    String ip;
    String username;
    String status;

    AuthLog(String ip, String username, String status) {
        this.ip = ip;
        this.username = username;
        this.status = status;
    }


}