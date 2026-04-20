package settlersOOPCheckpoint.main.java.hu.progmasters.settlers;

import settlersOOPCheckpoint.main.java.hu.progmasters.settlers.building.Archery;
import settlersOOPCheckpoint.main.java.hu.progmasters.settlers.building.Barracks;
import settlersOOPCheckpoint.main.java.hu.progmasters.settlers.building.Building;

public class BuildingFactory {

    private BuildingFactory() {}

    public static Building createBuilding(String buildingType) {
        switch (buildingType.toLowerCase()) {
            case "barracks":
                return new Barracks();
            case "archery":
                return new Archery();
            default:
                return null;
        }
    }
}