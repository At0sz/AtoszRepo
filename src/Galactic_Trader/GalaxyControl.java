package Galactic_Trader;

import java.util.List;

public class GalaxyControl {

    public int[] fleetCheck(List<SpaceShip> spaceShipList) {
        int[] result = new int[3];

        int fuelSpecific = (int) spaceShipList.stream()
                .filter(p -> p.getCurrentFuel() > 50)
                .count();

        int cargoSpecific = (int) spaceShipList.stream()
                .filter(p -> p.getCargoCapacity() > 1000)
                .count();

        int totalCargo = (int) spaceShipList.stream()
                .mapToInt(p -> p.getCargoCapacity())
                .sum();

        result[0] = fuelSpecific;
        result[1] = cargoSpecific;
        result[2] = totalCargo;


        return result;
    }
}
