package GrandPrixTelemetryAndRaceSystem;

public abstract class RaceVehicle {
    protected String pilotName;
    protected int durability;
    protected double baseSpeed;
    protected TireType currentTire;

    protected RaceVehicle(String pilotName, double baseSpeed, TireType currentTire) {
        this.pilotName = pilotName;
        this.baseSpeed = baseSpeed;
        this.currentTire = currentTire;
        this.durability = 100;
    }

    public abstract double calculateActualSpeed();

    public void degradeCondition(int amount){
        if(this.durability - amount <= 0){
            throw new EngineFailureException(this.pilotName + " motor is blowned up!!!");
        }else {
            this.durability -= amount;
        }
    }

    public String getPilotName() {
        return pilotName;
    }

    public int getDurability() {
        return durability;
    }

    public double getBaseSpeed() {
        return baseSpeed;
    }

    public TireType getCurrentTire() {
        return currentTire;
    }

    public void setCurrentTire(TireType currentTire) {
        this.currentTire = currentTire;
    }
}
