package SmartCityTrafficManager;

public class Bus extends Vehicle {
    private int capacity;

    public Bus(int emissionLevel, boolean hasEPass, String licensePlate, int capacity) {
        super(emissionLevel, hasEPass, licensePlate);
        this.capacity = capacity;
    }

    @Override
    public double getBasePrice() {
        if (capacity > 20) {
            return 500;
        } else {
            return 1000;
        }
    }
}
