package Gyakorlas2;

import java.util.*;

class Log {
    String employeeName;
    int floor;
    String status;

    public Log(String employeeName, int floor, String status) {
        this.employeeName = employeeName;
        this.floor = floor;
        this.status = status;
    }

    // Ezek kellenek, hogy a .distinct() felismerje az ugyanolyat
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return floor == log.floor && Objects.equals(employeeName, log.employeeName) && Objects.equals(status, log.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeName, floor, status);
    }
}

class Game {
    String title;
    int rating;
    double price;

    public Game(String title, int rating, double price) {
        this.title = title;
        this.rating = rating;
        this.price = price;
    }
}

class Device2 {
    String ipAddress;
    String type;
    int latency;
    boolean isOnline;

    public Device2(String ipAddress, String type, int latency, boolean isOnline) {
        this.ipAddress = ipAddress;
        this.type = type;
        this.latency = latency;
        this.isOnline = isOnline;
    }
}

public class gyakorlas2_7 {
    public static void main(String[] args) {
        List<Log> logs = Arrays.asList(
                new Log("Kovács Péter", 2, "VALID"),
                new Log("Ismeretlen", 5, "INVALID"),
                new Log("Nagy Anna", 3, "VALID"),
                new Log("Kovács Péter", 2, "VALID"),
                new Log("Szabó Márk", 1, "EXPIRED"),
                new Log("Nagy Anna", 3, "VALID"),
                new Log("Tóth Bence", 4, "VALID")
        );
        System.out.println();

        // --- IDE JÖN A TE STREAM KÓDOD ---
        logs.stream()
                .filter(log -> log.status.equals("VALID"))
                .distinct()
                .sorted(Comparator.comparingInt((Log p) -> p.floor).reversed())
                .forEach(p -> System.out.println(p.employeeName + " ezen az emeleten dolgozik : " + p.floor));

        double avgFloor = logs.stream()
                .filter(p -> p.status.equals("VALID"))
                .mapToInt(p -> p.floor)
                .average()
                .orElse(0);

        System.out.println("Emeletek átlaga: " + avgFloor);

        List<Game> games = Arrays.asList(
                new Game("Witcher 3", 95, 12000),
                new Game("Cyberpunk 2077", 88, 15000),
                new Game("Sims 4", 75, 8000),
                new Game("Elden Ring", 97, 18000),
                new Game("Fifa 24", 65, 20000)
        );

        System.out.println("\n--- 🏆 A Legjobb Értékelt Játékaink ---");

        games.stream()
                .filter(p -> p.rating > 80)
                .sorted(Comparator.comparingDouble((Game p) -> p.rating).reversed())
                .forEach(p -> System.out.println("TOP JÁTÉK: [" + p.title + "] - [" + p.rating + "] pont ([ " + p.price + "] Ft)"));


        List<Device2> network = Arrays.asList(
                new Device2("192.168.1.1", "Router", 2, true),
                new Device2("10.0.0.5", "Server", 45, true),
                new Device2("172.16.0.10", "Switch", 120, true),
                new Device2("192.168.1.50", "Server", 15, false),
                new Device2("10.0.0.10", "Server", 8, true),
                new Device2("172.16.0.20", "Switch", 5, true)
        );
        System.out.println();

        network.stream()
                .filter(p -> p.isOnline == true && (p.type.equals("Server") || p.type.equals("Switch")))
                .sorted(Comparator.comparingInt((Device2 p) -> p.latency).reversed())
                .forEach(p -> System.out.println("[" + p.ipAddress + "] ([" + p.type + "]) - Latency: [" + p.latency + "]ms"));


    }
}
