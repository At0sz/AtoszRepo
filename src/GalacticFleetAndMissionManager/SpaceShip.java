package GalacticFleetAndMissionManager;

import java.util.List;

public abstract class SpaceShip {
    private final String name;
    private final int fuelLevel; // 0-100
    private final int hullIntegrity; // 0-100
    private final List<String> crewNames;

    protected SpaceShip(String name, int fuelLevel, int hullIntegrity, List<String> crewNames) {
        this.name = name;
        this.fuelLevel = fuelLevel;
        this.hullIntegrity = hullIntegrity;
        this.crewNames = crewNames;
    }

    public abstract double calculateWarpSpeed();

    public List<String> getCrewNames() {
        return crewNames;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public int getHullIntegrity() {
        return hullIntegrity;
    }

    public String getName() {
        return name;
    }
}
