package Gyakorlas2;

import java.util.*;
import java.util.stream.Collectors;

public class gyakorlas2_9 {
    public static void main(String[] args) {


        List<Server> servers = Arrays.asList(
                new Server("192.168.1.1", "Main-Router", 40, true),
                new Server("192.168.1.5", "DB-Server", 95, true),
                new Server("192.168.1.10", "Web-Server", 20, false),
                new Server("192.168.1.12", "Backup-NAS", 88, true),
                new Server("10.0.0.1", "Internal-DNS", 10, true),
                new Server("10.0.0.50", "Log-Server", 99, true)
        );

        List<String> alertList = servers.stream()
                .filter(p -> p.isOnline && p.getLoad() > 80)
                .map(p -> {
                    return "Alert: [" + p.getIp() + "] ([" + p.getName() + "]) Load: [" + p.getLoad() + "]";
                })
                .toList();
        System.out.println();
        System.out.println(alertList);

        Map<String, List<Server>> serversMap = servers.stream()
                .collect(Collectors.groupingBy(Server::getIpAddress)); // Ez így sokkal tisztább!

        System.out.println();
// Írassuk is ki az eredményt, hogy lásd a csoportokat:
        serversMap.forEach((halozat, lista) -> {
            System.out.println("Hálózat: " + halozat + " -> Eszközök száma: " + lista.size());
        });

        System.out.println();
        serversMap.forEach((halozat, lista) -> {
            System.out.println("\n--- Hálózat: " + halozat + " ---");
            // A lista minden elemén végigfutunk és kiíratjuk
            lista.forEach(s -> System.out.println("  > " + s));
        });

        Map<String, List<Server>> sortedServersMap = servers.stream()
                .sorted(Comparator.comparingInt(Server::getLoad).reversed()) // 1. Csökkenő sorrendbe rakjuk az egészet
                .collect(Collectors.groupingBy(Server::getIpAddress, LinkedHashMap::new, Collectors.toList()));
        // A LinkedHashMap azért kell, hogy megőrizze a sorrendet!

        System.out.println();
        sortedServersMap.forEach((halozat, lista) -> {
            System.out.println("\n--- Sorted Hálózat: " + halozat + " ---");
            // A lista minden elemén végigfutunk és kiíratjuk
            lista.forEach(s -> System.out.println("  > " + s));
        });


    }
}


class Server {
    String ip;
    String name;
    int load; // CPU terheltség 0-100
    boolean isOnline;

    public Server(String ip, String name, int load, boolean isOnline) {
        this.ip = ip;
        this.name = name;
        this.load = load;
        this.isOnline = isOnline;
    }

    public int getLoad() {
        return load;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public String getIpAddress() {
        // A dupla visszaperjel (\\) kell, hogy a pontot sima karakterként kezelje
        String[] line = ip.split("\\.");
        if (line.length >= 3) {
            return line[0] + "." + line[1] + "." + line[2];
        }
        return ip; // Biztonsági mentés, ha nem IP-t kapna
    }

    public boolean isOnline() {
        return isOnline;
    }
    // Generálj gettereket (Alt+Insert IntelliJ-ben!)

    @Override
    public String toString() {
        return "Server{" +
                "ip='" + ip + '\'' +
                ", name='" + name + '\'' +
                ", load=" + load +
                ", isOnline=" + isOnline +
                '}';
    }
}