package GrandPrixTelemetryAndRaceSystem;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("🚥 GRAND PRIX SZIMULÁCIÓ INDUL 🚥\n");

        // 1. Race Director példányosítása (Nincs Singleton!)
        RaceDirector director = new RaceDirector();

        // 2. Autók (és pilóták) létrehozása
        FormulaCar hamilton = new FormulaCar("Lewis Hamilton", 320.0, TireType.SOFT);
        FormulaCar verstappen = new FormulaCar("Max Verstappen", 325.0, TireType.MEDIUM);
        FormulaCar leclerc = new FormulaCar("Charles Leclerc", 318.0, TireType.SOFT);
        FormulaCar alonso = new FormulaCar("Fernando Alonso", 315.0, TireType.HARD);
        FormulaCar norris = new FormulaCar("Lando Norris", 322.0, TireType.WET);

        // 3. Regisztráció
        director.registerVehicle(hamilton);
        director.registerVehicle(verstappen);
        director.registerVehicle(leclerc);
        director.registerVehicle(alonso);
        director.registerVehicle(norris);

        // 4. Verseny szimuláció (Kopás és Boxkiállás)
        System.out.println("--- 🏎️ VERSENYESEMÉNYEK ---");

        // Norris kiáll a boxba száraz gumikért
        norris.changeTires(TireType.MEDIUM);

        // Hamilton és Leclerc nagyon nyomja, kopik az autó
        hamilton.degradeCondition(75); // Durability: 25 (Veszélyzóna!)
        leclerc.degradeCondition(80);  // Durability: 20 (Veszélyzóna!)

        // Verstappen motorja felrobban (Exception teszt)
        try {
            System.out.println("Verstappen maximum gázt ad...");
            verstappen.degradeCondition(110);
        } catch (EngineFailureException e) {
            System.err.println("🔥 HIBA: " + e.getMessage());
        }

        System.out.println("\n--- 📊 TELEMETRIA ÉS RIPORTOK ---");

        // STREAM 1: Működőképes autók sorrendje (Tényleges sebesség alapján)
        System.out.println("\nÉlő autók sorrendje sebesség szerint:");
        List<RaceVehicle> sortedVehicles = director.getOperationalVehiclesSortedBySpeed();
        sortedVehicles.forEach(car ->
                System.out.printf("- %s: %.1f km/h (Durability: %d) [Gumi: %s]%n",
                        car.getPilotName(), car.calculateActualSpeed(), car.getDurability(), car.getCurrentTire())
        );

        // STREAM 2: Csoportosítás gumi szerint
        System.out.println("\nAutók csoportosítva gumikeverék alapján:");
        Map<TireType, List<RaceVehicle>> groupedByTire = director.groupVehiclesBytire();
        groupedByTire.forEach((tire, cars) -> {
            System.out.print("[" + tire + "]: ");
            String names = cars.stream().map(RaceVehicle::getPilotName).collect(Collectors.joining(", "));
            System.out.println(names);
        });

        // STREAM 3: Abszolút végsebesség
        System.out.println("\nAz eddigi legnagyobb elméleti sebesség a pályán: "
                + director.getTopSpeedOfAllTime() + " km/h");

        // STREAM 4: Veszélyzóna (<= 30 durability)
        System.out.println("\n⚠️ PILÓTÁK VESZÉLYZÓNÁBAN (Durability <= 30):");
        String dangerZone = director.getPilotNamesInDangerZone();
        System.out.println(dangerZone.isEmpty() ? "Mindenki biztonságban van." : dangerZone);
    }
}
