package SmartCityTrafficManager;

import java.util.ArrayList;
import java.util.List;

public class CityGate {
    private List<Vehicle> insideCityVehicles = new ArrayList<>();

    public void enterCity(Vehicle vehicle) throws VehicleAlreadyInCityException {
        if (insideCityVehicles.contains(vehicle)) {
            throw new VehicleAlreadyInCityException("Vehicle already entered.");
        } else {
            insideCityVehicles.add(vehicle);
            System.out.println(vehicle.getLicensePlate() + " entered into city gate.");
        }
    }

    public void exitCity(Vehicle vehicle) throws VehicleNotFoundException {
        if (insideCityVehicles.contains(vehicle)) {
            insideCityVehicles.remove(vehicle);
            System.out.println(vehicle.getLicensePlate() + " exited from city gate.");
        } else {
            throw new VehicleNotFoundException("Vehicle not found.");
        }
    }

    public void getTotalRevenue() {
        int sum = 0;
        for (Vehicle vehicle : insideCityVehicles) {
            sum += vehicle.calculateToll();
        }
        System.out.println("Total Revenue is " + sum + " Ft");
    }

    public void printTrafficReport(){
        System.out.println("-----The License Plates Inside The City-----");
        insideCityVehicles.stream()
                .map(p-> p.getLicensePlate())
                .sorted()
                .forEach(System.out::println);

        insideCityVehicles.stream()
                .max((v1, v2) -> Double.compare(v1.calculateToll(), v2.calculateToll()))
                .ifPresent(vehicle -> {
                    System.out.println("\n-----------------------------------");
                    System.out.println("The maximum amount of Toll payed by\n" +
                            vehicle.getLicensePlate() + "\n" +
                            vehicle.calculateToll() + " Ft\n" +
                            (vehicle.isHasEPass() ? "With E-pass" : "Without E-pass"));
                    System.out.println("-----------------------------------\n");
                });

        System.out.println("-----------------------------------\n");
        System.out.println("All The Bad Vehicles Inside The City");
        List<Vehicle> badVehicles = insideCityVehicles.stream()
                .filter(p->p.getEmissionLevel() < 3)
                .toList();
        badVehicles.forEach(System.out::println);
    }


}
