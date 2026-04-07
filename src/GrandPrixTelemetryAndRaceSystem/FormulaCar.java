package GrandPrixTelemetryAndRaceSystem;

public class FormulaCar extends RaceVehicle implements PitCapable{

    public FormulaCar(String pilotName, double baseSpeed, TireType currentTire) {
        super(pilotName, baseSpeed, currentTire);
    }

    @Override
    public void changeTires(TireType newTire) {
        setCurrentTire(newTire);
        System.out.println(getPilotName()+ " getting into the box, new tire: " + newTire.getName());
    }

    @Override
    public double calculateActualSpeed() {
        return getBaseSpeed() * getCurrentTire().getSpeedModifier();
    }
}
