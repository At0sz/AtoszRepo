package SmartCityTrafficManager;

public class VehicleAlreadyInCityException extends RuntimeException {
    public VehicleAlreadyInCityException(String message) {
        super(message);
    }
}
