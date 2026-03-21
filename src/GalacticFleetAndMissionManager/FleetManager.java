package GalacticFleetAndMissionManager;

import java.util.ArrayList;
import java.util.List;


public class FleetManager {
    private List<SpaceShip> spaceShips = new ArrayList<>();

    public void addShip(SpaceShip s) {
        this.spaceShips.add(s);
    }

    public void launchMission(SpaceShip spaceShip, Mission mission) throws LowFuelException, InadequateSpeedException, DamagedHullException {
        if (spaceShip.getFuelLevel() < 20) {
            throw new LowFuelException("Fuel is lower than 20, add more fuel to your mission");
        }
        if (spaceShip.getHullIntegrity() < 50) {
            throw new DamagedHullException("Too damaged to start your mission, repair your ship to continue");
        }
        if (spaceShip.calculateWarpSpeed() < mission.getRequiredWarpSpeed()) {
            throw new InadequateSpeedException("Inadequate speed for the mission");
        }

        System.out.println("---------------------");
        System.out.println("Mission to [" + mission.getDestination() + "] launched!");
        System.out.println("🚀🌍🌌☀✅");
        System.out.println("---------------------");
    }

    public List<SpaceShip> getTopThreeFastestShips() {
        return spaceShips.stream()
                .sorted((s1, s2) -> Double.compare(s2.calculateWarpSpeed(), s1.calculateWarpSpeed()))
                .limit(3)
                .toList();

    }

    public List<SpaceShip> getShipsForDangerLevel(int level) {
        return spaceShips.stream()
                .filter(s -> {
                    if (level <= 7) return true;

                    // Ha veszélyes (> 7), csak a Warship jöhet, aminek van ereje
                    if (s instanceof Warship w) {
                        return w.getWeaponPower() > 50;
                    }

                    return false;
                })
                .toList();
    }

    public List<String> getCrewReport() {
        return spaceShips.stream()
                .flatMap(s -> s.getCrewNames().stream())
                .distinct()
                .sorted()
                .toList();
    }
}
