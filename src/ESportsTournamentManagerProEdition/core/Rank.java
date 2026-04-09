package ESportsTournamentManagerProEdition.core;

public enum Rank {
    BRONZE(1.0),
    SILVER(2.0),
    GOLD(3.0),
    PLATINUM(4.0),
    DIAMOND(5.0);

    private double multiplier;

    Rank(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
