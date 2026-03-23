package IntergalacticTradingAndLogisticalCenter;

import IntergalacticTradingAndLogisticalCenter.Cargos.AlienArtifact;
import IntergalacticTradingAndLogisticalCenter.Cargos.Spice;
import IntergalacticTradingAndLogisticalCenter.Cargos.Technology;
import IntergalacticTradingAndLogisticalCenter.Exceptions.CargoFullException;
import IntergalacticTradingAndLogisticalCenter.Exceptions.CargoNotFoundException;
import IntergalacticTradingAndLogisticalCenter.Exceptions.IllegalCargoException;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // 1. Hajók létrehozása
        Freighter milano = new Freighter("Milano", 500.0);
        Freighter razorCrest = new Freighter("Razor Crest", 200.0);

        // 2. Áruk létrehozása
        Spice duneSpice = new Spice("S1", "Melange", 50.0, 1000.0, false, LocalDate.now().plusMonths(2), 0.9);
        Technology quantumCPU = new Technology("T1", "Quantum Processor", 20.0, 5000.0, true, LocalDate.now().plusYears(5), 100);
        AlienArtifact dangerousIdol = new AlienArtifact("A1", "Cursed Totem", 10.0, 200.0, false, LocalDate.now().plusYears(100), 9);
        AlienArtifact safeBead = new AlienArtifact("A2", "Glowing Bead", 5.0, 50.0, false, LocalDate.now().plusYears(10), 3);

        // 3. Teszt: Rakomány betöltése és Súlytúllépés
        System.out.println("=== RAKODÁS TESZT ===");
        try {
            milano.loadCargo(duneSpice);
            milano.loadCargo(quantumCPU);
            milano.loadCargo(dangerousIdol);
            System.out.println("Milano rakománya sikeresen betöltve.");

            // Ez elvileg hibát dob, ha túllépjük a Razor Crest 200-as limitjét egy nagy súlyú áruval
            razorCrest.loadCargo(new Spice("S2", "Heavy Dust", 250.0, 100.0, false, LocalDate.now(), 0.5));
        } catch (CargoFullException e) {
            System.err.println("HIBA: " + e.getMessage());
        }

        // 4. Teszt: Biztonsági ellenőrzés (Illegális áru)
        System.out.println("\n=== BIZTONSÁGI ELLENŐRZÉS TESZT ===");
        try {
            milano.securityCheck();
        } catch (IllegalCargoException e) {
            System.err.println("STOP: " + e.getMessage());
        }

        // 5. Teszt: Analitika
        System.out.println("\n=== FLOTTA ANALITIKA ===");
        FleetAnalytics analytics = new FleetAnalytics();
        analytics.getFleet().add(milano);
        analytics.getFleet().add(razorCrest);

        System.out.println("Kategóriánkénti összérték: " + analytics.getTotalValueByCategory());
        analytics.getAverageDangerLevel();
        analytics.getMostValuableFreighter();

        System.out.println("Lejáró törékeny áruk (3 hónapon belül):");
        analytics.getFragileExpiringSoon(LocalDate.now().plusMonths(3))
                .forEach(c -> System.out.println("- " + c.getName() + " (Lejár: " + c.getExpirationDate() + ")"));

        // 6. Teszt: TradeProcessor (Transzfer és Rollback)
        System.out.println("\n=== TRANSZFER TESZT ===");
        TradeProcessor processor = new TradeProcessor();

        // Sikeres transzfer (ha van hely)
        try {
            razorCrest.loadCargo(safeBead);
            System.out.println("Razor Crest súlya transzfer előtt: " + razorCrest.getInventory().size());
            processor.processTransfer(milano, razorCrest, "T1");
            System.out.println("Razor Crest súlya transzfer után: " + razorCrest.getInventory().size());
        } catch (Exception e) {
            System.err.println("Transzfer hiba: " + e.getMessage());
        }

        // Sikertelen transzfer (CargoNotFound)
        try {
            processor.processTransfer(milano, razorCrest, "NON-EXISTENT-ID");
        } catch (CargoNotFoundException e) {
            System.err.println("Várt hiba: " + e.getMessage());
        }
    }
}
