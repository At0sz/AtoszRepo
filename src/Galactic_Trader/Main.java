package Galactic_Trader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FleetRepository repo = new FleetRepository();
        SpaceStation station = new SpaceStation();
        GalaxyControl control = new GalaxyControl();

        System.out.println("--- 🚀 GALACTIC TRADER RENDSZER INDÍTÁSA ---");

        // 1. LÉPÉS: Betöltés a fájlból (ha már létezik)
        repo.loadFromFile("fleet.txt");

        // Ha üres a fájl (első futtatás), adjunk hozzá alap hajókat
        if (repo.getAllShips().isEmpty()) {
            System.out.println("Üres adatbázis, alap flotta generálása...");
            SpaceShip falcon = new CargoShip("Millennium Falcon", 2500);
            falcon.setPilot(new Pilot("Han Solo", 80));
            repo.addShip(falcon);

            SpaceShip xwing = new SpaceShip("X-Wing", 500);
            xwing.setPilot(new Pilot("Luke", 20));
            repo.addShip(xwing);
        }

        // 2. LÉPÉS: Keresés és Fejlesztés (Upgrade)
        // A Map miatt ez most O(1) sebességű (azonnali)
        SpaceShip myShip = repo.findShipByModel("Millennium Falcon");

        if (myShip != null) {
            System.out.println("\nFejlesztés előtt: " + myShip.getModel() + " raktér: " + myShip.getCargoCapacity());

            // Adjunk a pilótának pénzt, hogy tudjon fizetni az upgrade-ért
            myShip.getPilot().setCredits(1000);

            station.upGradeShip(myShip);
            System.out.println("Fejlesztés után: " + myShip.getModel() + " raktér: " + myShip.getCargoCapacity());
        }

        // 3. LÉPÉS: Flotta Analízis (Stream-ek a háttérben)
        System.out.println("\n--- Aktuális Flotta Statisztika ---");
        List<SpaceShip> currentFleet = repo.getAllShips();
        int[] stats = control.fleetCheck(currentFleet);

        System.out.println("Bevethető hajók (>50 fuel): " + stats[0]);
        System.out.println("Nagy teherbírású hajók (>1000 cargo): " + stats[1]);
        System.out.println("Teljes flotta kapacitás: " + stats[2]);

        // 4. LÉPÉS: Záró mentés (Az upgrade-elt adatokkal)
        repo.saveToFile("fleet.txt");
        System.out.println("\n--- Minden változás mentve a fleet.txt fájlba. ---");
    }
}