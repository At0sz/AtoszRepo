package SmartWarehouseManagement;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        // 1. Termékek létrehozása
        Electronics laptop = new Electronics("Asus ROG", 25, 2.5, 24);
        Electronics telo = new Electronics("iPhone 15", 22, 0.2, 12);

        Food tojas = new Food("Egg", 4, 0.05, LocalDate.of(2026, 4, 10));
        Food tej = new Food("Milk", 5, 1.0, LocalDate.of(2026, 3, 25)); // Ez jár le legkorábban
        Food kenyer = new Food("Bread", 20, 0.5, LocalDate.of(2026, 3, 30));

        // 2. Túl nehéz termék teszteléshez
        Electronics ipariGep = new Electronics("Industrial Server", 15, 600.0, 60);

        System.out.println("=== Raktár feltöltése ===");

        try {
            warehouse.addProduct(laptop);
            warehouse.addProduct(tojas);
            warehouse.addProduct(tej);
            warehouse.addProduct(kenyer);
            warehouse.addProduct(telo);
            System.out.println("Termékek sikeresen hozzáadva.");

            // Ez dobni fog egy AlreadyExistException-t
            System.out.println("\nPróbáljuk meg mégegyszer hozzáadni a laptopot...");
            warehouse.addProduct(laptop);

        } catch (AlreadyExistException e) {
            System.err.println("HIBA: " + e.getMessage());
        } catch (TooHeavyException e) {
            System.err.println("HIBA: " + e.getMessage());
        }

        // 3. Túl nehéz termék tesztelése külön
        try {
            System.out.println("\nPróbáljuk meg betenni az ipari gépet (600kg)...");
            warehouse.addProduct(ipariGep);
        } catch (TooHeavyException e) {
            System.err.println("ELKAPVA: " + e.getMessage());
        }

        System.out.println("\n=== Statisztikák ===");

        // Összsúly (Stream mapToInt)
        System.out.println("Raktár összsúlya: " + warehouse.calculateTotalWeight() + " kg");

        // Törékeny termékek száma (Stream filter + count)
        System.out.println("Törékeny termékek száma: " + warehouse.getFragileProductsCount());

        // 4. Lejárat tesztelése (A passzolt feladatod megoldása)
        System.out.println("\n=== Lejárati figyelmeztetés ===");
        Food nearestExpiry = warehouse.soonToExpire();
        if (nearestExpiry != null) {
            System.out.println("A leghamarabb lejáró étel: " + nearestExpiry.getName() +
                    " (Lejárat: " + nearestExpiry.getExpiryDate() + ")");
        } else {
            System.out.println("Nincs élelmiszer a raktárban.");
        }
    }
}
