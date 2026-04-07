package GrandPrixTelemetryAndRaceSystem;

public enum TireType {
    SOFT(1.2),
    MEDIUM(1.0),
    HARD(0.8),
    WET(0.9);

    private final double speedModifier;

    TireType(double speedModifier) {
        this.speedModifier = speedModifier;
    }

    public String getName() {
        return this.name();
    }
    public double getSpeedModifier() {
        return this.speedModifier;
    }
}
