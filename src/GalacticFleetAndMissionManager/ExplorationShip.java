package GalacticFleetAndMissionManager;

import java.util.List;

public class ExplorationShip extends SpaceShip {


    public ExplorationShip(String name, int fuelLevel, int hullIntegrity, List<String> crewNames) {
        super(name, fuelLevel, hullIntegrity, crewNames);
    }

    @Override
    public double calculateWarpSpeed() {
        return getFuelLevel() * 1.2;
    }
}
