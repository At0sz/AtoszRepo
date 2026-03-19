package SmartCityTrafficManager;

public abstract class Vehicle {
    private String licensePlate;
    private int emissionLevel;
    private boolean hasEPass;

    public Vehicle(int emissionLevel, boolean hasEPass, String licensePlate) {
        this.emissionLevel = emissionLevel;
        this.hasEPass = hasEPass;
        this.licensePlate = licensePlate;
    }

    public abstract double getBasePrice();

    public double calculateToll() {
        if (emissionLevel == 5) return 0;
        double price = getBasePrice();
        if (hasEPass) price *= 0.8;
        return price;
    }

    public int getEmissionLevel() {
        return emissionLevel;
    }

    public boolean isHasEPass() {
        return hasEPass;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public String toString() {
        return "Vehicle details: " + licensePlate + ", emission level: " + emissionLevel + ", hasEPass: " + (hasEPass?"Yes":"No");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return licensePlate.equals(vehicle.licensePlate);
    }
}
