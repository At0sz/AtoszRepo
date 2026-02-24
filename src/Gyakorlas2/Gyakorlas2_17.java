package Gyakorlas2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Gyakorlas2_17 {


    public static void main(String[] args) {
        System.out.println();
        // 1. Generálás
        List<Integer> traffic = generateTraffic(50);

        // 2. Szűrés (Stream) - Csak a 64 byte felettiek
        List<Integer> validTraffic = filterInvalidPackets(traffic);

        // 3. Statisztika
        analyzeTraffic(validTraffic);

        // 4. In-place módosítás (1500 -> -1)
        markJumboFrames(validTraffic);

        System.out.println();

        // 5. Kiíratás
        printTop10(validTraffic);
    }

    // Ide jönnek a metódusaid...
    public static List<Integer> generateTraffic(int traffic) {
        Random rand = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < traffic; i++) {
            list.add(rand.nextInt(1500 - 10 + 1) + 10);
        }
        return list;
    }

    public static List<Integer> filterInvalidPackets(List<Integer> traffic) {
        return traffic.stream()
                .filter(i -> i > 64)
                .toList();

    }

    public static void analyzeTraffic(List<Integer> validTraffic) {
        double average = validTraffic.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);

        System.out.printf("Average: %.2f\n", average);

        int max = validTraffic.stream().mapToInt(Integer::intValue).max().orElse(0);

        System.out.println("\nMax traffic is: " + max);

    }

    public static void markJumboFrames(List<Integer> validTraffic) {
        int count = 0;
        for (int i = 0; i < validTraffic.size(); i++) {
            if (validTraffic.get(i) == 1500) {
                validTraffic.set(i, -1);
                count++;
            }
        }

        System.out.println("\n--- Talált JumoFramek ---");
        System.out.println(count);


    }

    public static void printTop10(List<Integer> validTraffic) {
        validTraffic.stream()
                .sorted(Comparator.reverseOrder()) // 1. Csökkenő sorrend (legnagyobbak előre)
                .limit(10)                         // 2. Csak az első 10-et tartjuk meg
                .forEach(packet -> System.out.println("Csomag mérete: " + packet + " byte")); // 3. Kiíratás
    }

}
