package gyakorlas.gyak;

import java.util.*;

public class gyakszi3 {
    public static void main(String[] args) {
        List<String> rawData = Arrays.asList("1500", "error", "1200", "NaN", "800");

        List<Integer> cleanData = rawData.stream()
                .map(s -> {
                    try {
                        // Itt próbáljuk meg számmá alakítani
                        return Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        // Ha nem sikerült (pl. "error"), adjunk vissza null-t
                        return null;
                    }
                })
                // FELADAT 1: Szűrd ki a null értékeket, amiket a catch ágban gyártottunk!
                .filter(Objects::nonNull)
                .toList();

        System.out.println("Tiszta adatok: " + cleanData);

        // FELADAT 2: Számold ki a 'cleanData' átlagát egy Stream segítségével!
        // Tipp: Használd a .mapToInt() után az .average() metódust!

        double averageSize = cleanData.stream()
                .mapToInt(n -> n)         // Átalakítjuk IntStream-mé
                .average()                // Kiszámoljuk az átlagot (OptionalDouble-t ad)
                .orElse(0.0);

        System.out.println("Az átlagos csomagméret: " + averageSize);
    }
}
