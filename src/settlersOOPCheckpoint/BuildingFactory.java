package settlersOOPCheckpoint;

import settlersOOPCheckpoint.building.Archery;
import settlersOOPCheckpoint.building.Barracks;
import settlersOOPCheckpoint.building.Building;

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