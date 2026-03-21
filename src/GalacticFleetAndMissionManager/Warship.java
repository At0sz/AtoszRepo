package GalacticFleetAndMissionManager;

import java.util.List;

public class Warship extends SpaceShip{
    private final int weaponPower;

    public Warship(String name, int fuelLevel, int hullIntegrity, List<String> crewNames, int weaponPower) {
        super(name, fuelLevel, hullIntegrity, crewNames);
        this.weaponPower = weaponPower;
    }

    @Override
    public double calculateWarpSpeed() {
        return (getFuelLevel() * 1.0)- (this.weaponPower * 0.1);
    }

    public int getWeaponPower() {
        return weaponPower;
    }
}
