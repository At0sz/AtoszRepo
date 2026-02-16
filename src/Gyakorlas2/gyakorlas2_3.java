package Gyakorlas2;

import java.util.*;

public class gyakorlas2_3 {
    public static void main(String[] args) {

        List<Car> garage = Arrays.asList(
                new Car("Tesla Model 3", 25000, true),
                new Car("Suzuki Swift", 8000, true),
                new Car("BMW M5", 45000, true),
                new Car("VW Golf", 12000, true)
        );

//        var availableCars = garage.stream()
//                .filter(p-> p.isAvailable)
//                .map(p-> {
//                    int days = 3;
//                    return "Autó: " + p.model + " 3 napi díja: " + (p.dailyRate * days) + " Ft, napi díj: " + p.dailyRate;
//                });
//
//
//        System.out.println();
//        System.out.println("---Elérhető autók---");
//        availableCars.forEach(System.out::println);
//
//        System.out.println();
//        garage.stream()
//                .filter(p -> p.model.equals("BMW M5"))
//                .findFirst() // Megkeresi az első találatot
//                .ifPresent(car -> car.rent());
//
//
//        System.out.println("\n---Frissített lista a foglalás után---");
//        garage.stream()
//                .filter(p -> p.isAvailable)
//                .map(p -> "Autó: " + p.model + " - Szabad")
//                .forEach(System.out::println);
//        System.out.println();
//
//        double totalDailyIncome = garage.stream()
//                .mapToDouble(p -> p.dailyRate) // Átalakítjuk a Streamet egy "számfolyammá"
//                .sum(); // Összeadjuk
//
//        System.out.println("Napi potenciális bevétel: " + totalDailyIncome + " Ft");
//        System.out.println();
//        garage.stream()
//                .filter(p -> p.isAvailable)
//                .min(Comparator.comparingDouble(p -> p.dailyRate)) // Megkeresi a legkisebb dailyRate-et
//                .ifPresent(bestDeal -> System.out.println("Legjobb ajánlatunk: " + bestDeal.model));
//
//        Map<Boolean, List<Car>> groupedCars = garage.stream()
//                .collect(Collectors.groupingBy(p -> p.isAvailable));
//
//        System.out.println("Szabad autók száma: " + groupedCars.get(true).size());
//
//        System.out.println("Foglalt autók száma: " + groupedCars.get(false).size());
//        System.out.println();
//        // A Map-ből lekérjük a TRUE kulcshoz tartozó listát (Szabadok)
//        System.out.println("=== SZABAD AUTÓK ===");
//        if (groupedCars.containsKey(true)) {
//            groupedCars.get(true).forEach(car -> System.out.println("- " + car.model));
//        }
//        System.out.println();
//// Lekérjük a FALSE kulcshoz tartozót (Foglaltak)
//        System.out.println("\n=== FOGLALT AUTÓK ===");
//        if (groupedCars.containsKey(false)) {
//            groupedCars.get(false).forEach(car -> System.out.println("- " + car.model));
//        }

        System.out.println();
        garage.stream()
                .filter(p -> p.isAvailable && p.dailyRate < 30000)
                .sorted(Comparator.comparingDouble(p -> p.dailyRate))
                .forEach(car -> System.out.println("Ajánlat:  [" + car.model + "] - [" + car.dailyRate + "] Ft/nap"));


    }
}

class Car {
    String model;
    double dailyRate;
    boolean isAvailable;

    public Car(String model, double dailyRate, boolean isAvailable) {
        this.model = model;
        this.dailyRate = dailyRate;
        this.isAvailable = isAvailable;
    }

    public void rent() {
        if (this.isAvailable) {
            this.isAvailable = false;
            System.out.println(this.model + " sikeresen lefoglalva!");
        } else {
            System.out.println("Hiba: " + this.model + " már foglalt!");
        }
    }

}
