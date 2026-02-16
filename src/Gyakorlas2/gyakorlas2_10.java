package Gyakorlas2;

import java.util.*;
import java.util.stream.Collectors;

public class gyakorlas2_10 {

    public static void main(String[] args) {


        List<Port> patchPanel = Arrays.asList(
                new Port(1, "HR-Office", true, 500),
                new Port(2, "IT-Basement", true, 12000),
                null, // 🚩 Valaki kihúzta a kábelt!
                new Port(3, "HR-Office", false, 0),
                new Port(4, "Meeting-Room", true, 1500),
                new Port(5, "IT-Basement", true, 8000),
                new Port(6, "Kitchen", true, 200)
        );

        Map<String, Integer> cleanList = patchPanel.stream()
                .filter(Objects::nonNull)
                .filter(p -> p.isLive)
                .collect(Collectors.groupingBy(
                        Port::getRoom,
                        Collectors.summingInt(Port::getDataUsage)
                ));

        System.out.println("---Szobák forgalmai---");
        cleanList.forEach((room, dataUsage) -> {
            System.out.println("Szoba: [" + room + "] Adathasználat: " + dataUsage);
        });

        Optional<Map.Entry<String, Integer>> maxNumber = cleanList.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        maxNumber.ifPresent(entry -> {
            System.out.println("\n🏆 A legforgalmasabb szoba:");
            System.out.println("Szoba neve: " + entry.getKey());
            System.out.println("Összesített forgalom: " + entry.getValue() + " MB");
        });


        System.out.println("\n--- 🏆 SZOBA TOP LISTA (FORMÁZOTT) ---");
        System.out.printf("%-20s | %10s%n", "SZOBA NEVE", "FORGALOM"); // Fejléc
        System.out.println("------------------------------------------");

        cleanList.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(p -> {
                    // %-20s -> String, 20 karakter széles, balra zárva (-)
                    // %10d  -> Egész szám (decimal), 10 karakter széles, jobbra zárva
                    // %n     -> Platformfüggetlen új sor (mint a \n)
                    System.out.printf("%-20s | %10d MB%n", p.getKey(), p.getValue());
                });


    }
}

class Port {
    int id;
    String room;
    boolean isLive;
    int dataUsage; // MB-ban

    public Port(int id, String room, boolean isLive, int dataUsage) {
        this.id = id;
        this.room = room;
        this.isLive = isLive;
        this.dataUsage = dataUsage;
    }
    // Getterek, toString jöhet ezerrel (Alt+Insert!)

    public int getId() {
        return id;
    }

    public String getRoom() {
        return room;
    }

    public boolean isLive() {
        return isLive;
    }

    public int getDataUsage() {
        return dataUsage;
    }

    @Override
    public String toString() {
        return "Port{" +
                "id=" + id +
                ", room='" + room + '\'' +
                ", isLive=" + isLive +
                ", dataUsage=" + dataUsage +
                '}';
    }
}