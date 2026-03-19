package SmartCityTrafficManager;

public class SmartCityTrafficManagerMain {
    public static void main(String[] args) {
        CityGate gate = new CityGate();

        // 1. Különböző járművek létrehozása
        Car tesla = new Car(5, true, "TESLA-01"); // Ingyenes behajtás (Emission 5)
        Car suzuki = new Car(2, false, "AAA-111"); // 1000 Ft
        Truck kamion = new Truck(1, false, "BIG-001", 15); // 15000 Ft (Súly miatt)
        Bus kisBusz = new Bus(3, true, "BUS-020", 15); // 1000 * 0.8 = 800 Ft
        Bus nagyBusz = new Bus(4, false, "BUS-100", 50); // 500 Ft (Capacity > 20)

        System.out.println("=== Beléptetés indítása ===");

        try {
            gate.enterCity(tesla);
            gate.enterCity(suzuki);
            gate.enterCity(kamion);
            gate.enterCity(kisBusz);
            gate.enterCity(nagyBusz);

            // Ez hibát fog dobni (Már bent van)
            System.out.println("Próbáljuk meg még egyszer beléptetni a Teslát...");
            gate.enterCity(tesla);

        } catch (VehicleAlreadyInCityException e) {
            System.err.println("Hiba történt: " + e.getMessage());
        }

        System.out.println("\n=== Bevétel ellenőrzése ===");
        gate.getTotalRevenue();

        System.out.println("\n=== Riport generálása ===");
        gate.printTrafficReport();

        System.out.println("\n=== Kiléptetés teszt ===");
        try {
            gate.exitCity(suzuki);
            System.out.println("Suzuki kilépett.");

            // Próbáljuk meg kiléptetni mégegyszer
            gate.exitCity(suzuki);
        } catch (VehicleNotFoundException e) {
            System.err.println("Hiba kiléptetéskor: " + e.getMessage());
        }

        System.out.println("\n=== Végső állapot riportja ===");
        gate.printTrafficReport();
    }
}
