package gyakorlas.gyak;

import java.util.*;
import java.util.stream.*;


class Node {
    String name;
    String type; // SERVER, ROUTER, SWITCH
    String ip;
    int activeConnections;

    Node(String name, String type, String ip, int activeConnections) {
        this.name = name;
        this.type = type;
        this.ip = ip;
        this.activeConnections = activeConnections;
    }

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}


public class gyakszi11 {
    public static void main(String[] args) {

        List<Node> nodes = Arrays.asList(
                new Node("Core-Router", "ROUTER", "192.168.1.1", 150),
                new Node("Web-Server", "SERVER", "10.0.0.10", 500),
                new Node("Backup-Server", "SERVER", "10.0.0.11", 20),
                new Node("Floor-Switch", "SWITCH", "192.168.1.50", 10),
                new Node("Edge-Router", "ROUTER", "172.16.0.1", 300),
                new Node("DB-Server", "SERVER", "10.0.0.20", 800)
        );

        List<String> serverNodes = nodes.stream()
                .filter(p -> p.type.equals("SERVER") && p.activeConnections > 100)
                // RENDEZÉS (Csökkenő): m2 és m1 sorrendje határozza meg az irányt
                .sorted((n1, n2) -> Integer.compare(n2.activeConnections, n1.activeConnections))
                .map(p -> p.name + " -> " + p.ip + " (" + p.activeConnections + " units)")
                .toList();

        boolean found = nodes.stream()
                .anyMatch(p -> p.ip.startsWith("192.168"));

        serverNodes.forEach(System.out::println);
        System.out.println("\nVan 192.168.-al kezdödő ip?" + (found ? " Igen" : " Nem"));

        List<String> loadReport = nodes.stream()
                .map(p -> {
                    try {
                        // Itt jön a matek: az egység kiszámolása
                        int unit = p.activeConnections / (p.activeConnections > 0 ? p.activeConnections : 0);
                        // Direkt generálunk egy hibát, ha a kapcsolat 0:
                        if (p.activeConnections == 0) {
                            int crash = 10 / 0; // Itt dob egy ArithmeticException-t
                        }
                        return p.name + " terhelés: " + (p.activeConnections / 100) + " egység";
                    } catch (ArithmeticException e) {
                        // Ha nullával osztottunk, ide ugrik a kód
                        return p.name + " -> OFFLINE (Nincs aktív kapcsolat)";
                    }
                })
                .toList();

        loadReport.forEach(System.out::println);

        List<String> jsonStrings = nodes.stream()
                .map(p -> "{\"name\": \"" + p.name + "\", \"ip\": \"" + p.ip + "\"}")
                .toList();

        jsonStrings.forEach(System.out::println);

    }
}