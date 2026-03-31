package NeonGrid.Entities;

public enum Devices {
    COFFEMAKER(0.5),
    TERMINAL(1),
    SERVER(2),
    CLOUD(3.5),
    LUXURYSERVER(5);

    private final double energyCost;

    Devices(double energyCost) {
        this.energyCost = energyCost;
    }

    public double getEnergyCost() {
        return energyCost;
    }
}
