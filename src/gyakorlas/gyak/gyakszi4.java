package gyakorlas.gyak;

import java.util.*;
import java.util.stream.*;

public class gyakszi4 {
    public static void main(String[] args) {
        List<String> rawPorts = Arrays.asList("80", "443", "error", "80", "8080", "unknown", "22", "443");

        // 1. Készítsük el a tiszta listát
        List<Integer> validPorts = rawPorts.stream()
                .map(s -> {
                    try {
                        return Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                // IDE JÖNNEK AZ ÚJ METÓDUSOK:
                // Tipp: Használd a .distinct() -et az ismétlések ellen
                // Tipp: Használd a .sorted() -at a sorrendhez
                .toList();

        System.out.println("Rendezett egyedi portok: " + validPorts);

        // 2. Számold meg a darabszámot!
        // Vigyázz, a .count() metódus long-ot ad vissza!
        long portCount = validPorts.stream()
                .count();

        System.out.println("Összesen " + portCount + " érvényes portunk van.");
    }
}
