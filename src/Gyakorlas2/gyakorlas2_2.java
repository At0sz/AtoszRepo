package Gyakorlas2;

import java.util.*;

public class gyakorlas2_2 {
    static void main() {

        List<Ingredient2> inventory = Arrays.asList(
                new Ingredient2("Liszt", 2.0, "kg"),
                new Ingredient2("Tojas", 0, "db"),
                new Ingredient2("Tej", 1.5, "l")
        );


        var toCook = inventory.stream()
                .filter(p -> p.amount > 0)
                .map(p -> p.name.toUpperCase())
                .toList();

        inventory.forEach(ing -> {
            try {
                double portion = ing.amount / 2;
                if (ing.amount == 0) throw new Exception("Kifogyott!"); // Manuális hiba dobás
                System.out.println(ing.name.toUpperCase() + ": egy adaghoz kell " + portion + " " + ing.unit);
            } catch (Exception e) {
                System.out.println("HIBA: " + ing.name + " nem használható (" + e.getMessage() + ")");
            }
        });

    }
}

class Ingredient2 {
    String name;
    double amount;
    String unit;


    public Ingredient2(String name, double amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }
}
