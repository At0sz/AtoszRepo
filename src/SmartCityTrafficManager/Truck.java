package SmartCityTrafficManager;

public class Truck extends Vehicle {
    private int weight;

    public Truck(int emissionLevel, boolean hasEPass, String licensePlate, int weight) {
        super(emissionLevel, hasEPass, licensePlate);
        this.weight = weight;
    }

    @Override
    public double getBasePrice() {
        return 1000 * this.weight;
    }
}
