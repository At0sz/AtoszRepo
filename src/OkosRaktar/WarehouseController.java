package OkosRaktar;

import java.util.List;
import java.util.Scanner;

public class WarehouseController {
    private Warehouse warehouse = new Warehouse();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new WarehouseController().runMenu();
    }

    public void runMenu() {
        int choice = 0;
        do {
            printMenu();
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Puffer tisztítás
                handleChoice(choice);
            } catch (Exception e) {
                System.out.println("❌ Hiba: Érvénytelen bemenet! Számot adj meg.");
                scanner.nextLine(); // Hibás bemenet kidobása
            }
        } while (choice != 7);
    }

    private void printMenu() {
        System.out.println("\n--- 🏭 OKOS RAKTÁR MENÜ ---");
        System.out.println("1. Termék rögzítése");
        System.out.println("2. Teljes leltár listázása");
        System.out.println("3. Keresés kategória alapján");
        System.out.println("4. Készlet módosítása (+/-)");
        System.out.println("5. Kifogyott termékek listája");
        System.out.println("6. Raktár összértéke");
        System.out.println("7. Kilépés");
        System.out.print("Választás: ");
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 -> addProductFlow();
            case 2 -> printList(warehouse.getProductList(), "Teljes leltár");
            case 3 -> {
                System.out.print("Keresett kategória: ");
                String cat = scanner.nextLine();
                printList(warehouse.getProductByCategory(cat), "Kategória találatok");
            }
            case 4 -> updateStockFlow();
            case 5 -> printList(warehouse.getOutOfStockProducts(), "Kifogyott termékek");
            case 6 -> System.out.println("\n💰 Raktár teljes értéke: " + warehouse.getTotalWarehouseValue() + " Ft");
            case 7 -> System.out.println("Viszontlátásra! 👋");
            default -> System.out.println("⚠️ Ismeretlen menüpont!");
        }
    }

    private void addProductFlow() {
        try {
            System.out.print("Név: "); String name = scanner.nextLine();
            System.out.print("Kategória: "); String cat = scanner.nextLine();
            System.out.print("Ár (Ft): "); int price = scanner.nextInt();
            System.out.print("Kezdő készlet (db): "); int stock = scanner.nextInt();
            scanner.nextLine();
            warehouse.addProduct(new Product(name, cat, price, stock));
            System.out.println("✅ Termék sikeresen felvéve!");
        } catch (Exception e) {
            System.out.println("❌ Hiba a bevitel során! A termék nem lett mentve.");
            scanner.nextLine();
        }
    }

    private void updateStockFlow() {
        System.out.print("Módosítandó termék neve: ");
        String name = scanner.nextLine();
        System.out.print("Mennyiség változása (pl. 5 vagy -5): ");
        int amount = scanner.nextInt();
        scanner.nextLine();
        if (warehouse.updateStock(name, amount)) {
            System.out.println("✅ Készlet frissítve!");
        } else {
            System.out.println("❌ Termék nem található!");
        }
    }

    private void printList(List<Product> list, String title) {
        System.out.println("\n--- " + title + " ---");
        if (list.isEmpty()) {
            System.out.println("Nincs megjeleníthető adat.");
        } else {
            list.forEach(System.out::println);
        }
    }
}