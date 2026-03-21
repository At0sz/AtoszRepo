package GalacticFleetAndMissionManager;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static final String EGYEB_HIBA = "Egyéb hiba: ";

    public static void main(String[] args) {
        FleetManager manager = new FleetManager();

        // --- 1. Flotta feltöltése (Legénységgel, duplikációkkal a teszthez) ---
        List<String> crew1 = Arrays.asList("Han Solo", "Chewbacca", "Leia Organa");
        List<String> crew2 = Arrays.asList("Luke Skywalker", "R2-D2", "Han Solo"); // Han duplikálva
        List<String> crew3 = Arrays.asList("Spock", "James T. Kirk", "Nyota Uhura");

        Warship falcon = new Warship("Millennium Falcon", 80, 90, crew1, 100);
        Warship xWing = new Warship("X-Wing", 15, 80, crew2, 40); // Kevés üzemanyag teszt
        ExplorationShip enterprise = new ExplorationShip("USS Enterprise", 100, 40, crew3); // Sérült hull teszt
        ExplorationShip scout = new ExplorationShip("Scout-01", 30, 100, Arrays.asList("T-800"));

        manager.addShip(falcon);
        manager.addShip(xWing);
        manager.addShip(enterprise);
        manager.addShip(scout);

        // --- 2. Küldetések indítása (Exception handling teszt) ---
        Mission kesselRun = new Mission("Kessel", 5, 50);
        Mission deathStar = new Mission("Death Star", 10, 20);

        System.out.println("=== KÜLDETÉS INDÍTÁSI TESZTEK ===");

        // Teszt 1: Siker
        try {
            manager.launchMission(falcon, kesselRun);
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getMessage());
        }

        // Teszt 2: Kevés üzemanyag
        try {
            manager.launchMission(xWing, kesselRun);
        } catch (LowFuelException e) {
            System.err.println("ÜZEMANYAG HIBA: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(EGYEB_HIBA + e.getMessage());
        }

        // Teszt 3: Sérült hajótest
        try {
            manager.launchMission(enterprise, kesselRun);
        } catch (DamagedHullException e) {
            System.err.println("SZERKEZETI HIBA: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(EGYEB_HIBA + e.getMessage());
        }

        // Teszt 1: Siker
        try {
            manager.launchMission(falcon, deathStar);
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getMessage());
        }

        // Teszt 2: Kevés üzemanyag
        try {
            manager.launchMission(xWing, deathStar);
        } catch (LowFuelException e) {
            System.err.println("ÜZEMANYAG HIBA: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(EGYEB_HIBA + e.getMessage());
        }

        // Teszt 3: Sérült hajótest
        try {
            manager.launchMission(enterprise, deathStar);
        } catch (DamagedHullException e) {
            System.err.println("SZERKEZETI HIBA: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(EGYEB_HIBA + e.getMessage());
        }

        // --- 3. Stream Jelentések ---
        System.out.println("\n=== TOP 3 LEGGYORSABB HAJÓ ===");
        manager.getTopThreeFastestShips().forEach(s ->
                System.out.println(s.getName() + " - Speed: " + s.calculateWarpSpeed()));

        System.out.println("\n=== VESZÉLYES KÜLDETÉSRE ALKALMAS HAJÓK (Level 10) ===");
        manager.getShipsForDangerLevel(10).forEach(s ->
                System.out.println(s.getName() + " (Csak erős Warship maradhat!)"));

        System.out.println("\n=== LEGÉNYSÉGI JELENTÉS (ABC, egyedi nevek) ===");
        manager.getCrewReport().forEach(name -> System.out.println("- " + name));
    }
}
