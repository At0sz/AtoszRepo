package gyakorlas.gyak;

import java.util.*;
import java.util.stream.*;

public class gyakszi12 {
    public static void main(String[] args) {

        List<Order> orders = Arrays.asList(
                new Order("ELECTRONICS", "Laptop", 1200.0),
                new Order("CLOTHING", "T-Shirt", 25.0),
                new Order("ELECTRONICS", "Mouse", 15.0),
                new Order("FOOD", "Apple", 2.5),
                new Order("CLOTHING", "Jeans", 60.0),
                new Order("FOOD", "Pizza", 12.0)
        );

        Map<String, Double> salesByCategory = orders.stream()
                .collect(Collectors.groupingBy(
                        o -> o.category,
                        Collectors.summingDouble(o -> o.price)
                ));

        System.out.println("Bevétel kategóriánként: " + salesByCategory);

        List<Order> cleanedList = orders.stream()
                .filter(p -> p.price > 20)
                .sorted((n1, n2) -> Double.compare(n1.price, n2.price))
                .toList();

        double sum = cleanedList.stream().mapToDouble(o -> o.price).sum();

        // 3. Kiíratás és formázás
        cleanedList.forEach(o -> System.out.println(o.product + ": " + o.price + "$"));

// %.2f -> 2 tizedesjegy, \n -> új sor
        System.out.printf("A szűrt tételek végösszege: %.2f$\n", sum);

        System.out.println("\n");

        String log = "Port1:Active:1000,Port2:Inactive:0,Port3:Active:100";

        List<String> logList = Arrays.asList(log.split(","));

        List<Integer> speeds = logList.stream()
                .map(p -> {
                    String[] split = p.split(":");
                    return Integer.valueOf(split[2]);
                })
                .toList();

        System.out.println("Sebbességek: " + speeds);
        System.out.println("\n");

        Double logAverage = logList.stream()
                .filter(p -> p.contains("Active"))
                .mapToDouble(p -> Integer.valueOf(p.split(":")[2]))
                .average().orElse(0.0);

        System.out.printf("Aktív portok átlagsebessége: %.2f Mbps\n", logAverage);


    }

}


class Order {
    String category;
    String product;
    double price;

    Order(String category, String product, double price) {
        this.category = category;
        this.product = product;
        this.price = price;
    }
}