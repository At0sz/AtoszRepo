package GalacticFleetAndMissionManager;

public class Mission {
    private final String destination;
    private final int requiredWarpSpeed;
    private final int dangerLevel; // 1-10

    public Mission(String destination, int dangerLevel, int requiredWarpSpeed) {
        this.destination = destination;
        this.dangerLevel = dangerLevel;
        this.requiredWarpSpeed = requiredWarpSpeed;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public String getDestination() {
        return destination;
    }

    public int getRequiredWarpSpeed() {
        return requiredWarpSpeed;
    }
}
