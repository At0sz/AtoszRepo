package Gyakorlas2;

import java.util.*;
import java.util.stream.*;
import java.time.LocalDate;


interface Perishable {
    boolean isExpired();
}


abstract class Ingredient implements Perishable {
    String name;
    double quantity;
    LocalDate expirationDate;

    Ingredient(String name, double quantity, LocalDate expirationDate) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return name + " (" + quantity + ")";
    }
}


class LiquidIngredient extends Ingredient {
    double density;

    LiquidIngredient(String name, double quantity, LocalDate exp, double density) {
        super(name, quantity, exp);
        this.density = density;
    }
}

class SolidIngredient extends Ingredient {
    String unit;

    SolidIngredient(String name, double quantity, LocalDate exp, String unit) {
        super(name, quantity, exp);
        this.unit = unit;
    }
}

//FŐ PROGRAM
public class SmartKitchenInventory {
    public static void main(String[] args) {
        List<Ingredient> storage = new ArrayList<>(Arrays.asList(
                new LiquidIngredient("Tej", 0.5, LocalDate.of(2026, 2, 10), 1.03),
                new SolidIngredient("Liszt", 10.0, LocalDate.of(2027, 12, 31), "kg"),
                new SolidIngredient("Tojás", 2.0, LocalDate.of(2026, 3, 1), "db"),
                new LiquidIngredient("Olaj", 4.0, LocalDate.of(2026, 5, 20), 0.92),
                new SolidIngredient("Vaj", 0.2, LocalDate.of(2026, 2, 15), "kg")
        ));

        System.out.println("--- LEJÁRT TERMÉKEK ---");


        storage.stream()
                .filter(i -> i.isExpired())
                .forEach(i -> System.out.println(i));

        System.out.println("\n--- BEVÁSÁRLÓLISTA (Mennyiség < 5) ---");

        storage.stream()
                .filter(i -> i.quantity < 5)
                .forEach(i -> System.out.println("Szükséges feltölteni: " + i));

        System.out.println("\n--- ÖSSZES SZILÁRD ALAPANYAG SÚLYA ---");

        double weight = storage.stream()
                .filter(i -> i instanceof SolidIngredient)
                .mapToDouble(i -> i.quantity)
                .sum();

        System.out.println(weight);

        System.out.println("\n--- CSOPORTOSÍTÁS TÍPUS SZERINT ---");
        Map<String, List<Ingredient>> groupedItems = storage.stream()
                .collect(Collectors.groupingBy(i -> i instanceof LiquidIngredient ? "Folyékony" : "Szilárd"));

        groupedItems.forEach((type, items) -> System.out.println(type + ": " + items));


        System.out.println("\n--- MENNYISÉGI STATISZTIKA ---");
        DoubleSummaryStatistics stats = storage.stream()
                .mapToDouble(i -> i.quantity)
                .summaryStatistics();

        System.out.println("Átlagos mennyiség: " + stats.getAverage());
        System.out.println("Max mennyiség: " + stats.getMax());
        System.out.println("Min mennyiség: " + stats.getMin());
    }
}
