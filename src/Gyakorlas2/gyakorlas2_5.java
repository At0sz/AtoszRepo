package Gyakorlas2;

import java.util.*;


public class gyakorlas2_5 {
    public static void main(String[] args) {

        List<Product> cart = Arrays.asList(
                new Product("SSD 1TB", 35000, "Hardver"),
                new Product("Gamer Egér", 12000, "Periféria"),
                new Product("Monitor", 85000, "Periféria"),
                new Product("Billentyűzet", 18000, "Periféria"),
                new Product("RTX 4060", 145000, "Hardver")
        );

        Map<String, Integer> stock = new HashMap<>();
        stock.put("Gamer Egér", 5);
        stock.put("Monitor", 0);
        stock.put("RTX 4060", 2);

        cart.stream()
                .filter(p -> p.category.equals("Periféria"))
                .forEach(p -> System.out.println("😍 Akciós Periféria!! Termékünk a " + p.name + " most akciós áron az öné lehet, régi ára: " + p.price + " aminek az új ára: " + (p.price * 0.9)));

        System.out.println();
        System.out.println("🎉 A 100 ezer forint alatti termékeink:");
        cart.stream()
                .filter(p -> p.price < 100000)
                // Megadjuk, hogy az ár (price) alapján hasonlítson össze, és fordítsa meg (reversed)
                .sorted(Comparator.comparingDouble((Product p) -> p.price).reversed())
                .forEach(p -> System.out.println("Termék neve: " + p.name + " az ára pedig: " + p.price));

        System.out.println();

        double total = cart.stream()
                .mapToDouble(p -> p.price)
                .sum();

        System.out.println("Végösszeg (szállítással): " + (total + 2000) + " Ft");

        System.out.println("\n✨ Elérhető termékeink: \n");
        cart.stream()
                .filter(p -> stock.getOrDefault(p.name, 0) > 0)
                .forEach(p -> System.out.println("Termék: " + p.name + " ami ezen az áron: " + p.price + " és jelenleg ennyi van belőle : " + stock.get(p.name)));


    }
}

class Product {
    String name;
    double price;
    String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}