package SmartCityTrafficManager;

public class Car extends Vehicle {


    public Car(int emissionLevel, boolean hasEPass, String licensePlate) {
        super(emissionLevel, hasEPass, licensePlate);
    }

    @Override
    public double getBasePrice() {
        return 1000;
    }
}
