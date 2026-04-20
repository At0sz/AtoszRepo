package settlersOOPCheckpoint.main.java.hu.progmasters.settlers.building;

import settlersOOPCheckpoint.main.java.hu.progmasters.settlers.resource.Resource;
import settlersOOPCheckpoint.main.java.hu.progmasters.settlers.unit.Unit;

public abstract class Building {

    private int currentTurn;
    private final int unitTimeToTurn;
    private final int resourceTimeToTurn;
    private final int resourceAmount;

    protected Building(int unitTimeToTurn, int resourceTimeToTurn, int resourceAmount) {
        if (unitTimeToTurn <= 0 || resourceTimeToTurn <= 0) {
            throw new IllegalArgumentException("Production cycles must be positive!");
        }
        if (resourceAmount < 0) {
            throw new IllegalArgumentException("Resource quantity must be positive!");
        }
        this.unitTimeToTurn = unitTimeToTurn;
        this.resourceTimeToTurn = resourceTimeToTurn;
        this.resourceAmount = resourceAmount;
        this.currentTurn = -1;
    }

    public void advanceTurn() {
        currentTurn++;
    }

    public boolean canProduceUnit() {
        return currentTurn > 0 && currentTurn % unitTimeToTurn == 0;
    }

    public boolean canProduceResource() {
        return currentTurn > 0 && currentTurn % resourceTimeToTurn == 0;
    }

    public int getTurnUntilUnitProduced() {
        int modulo = currentTurn % unitTimeToTurn;
        return unitTimeToTurn - modulo;
    }

    public int getTurnUntilResourceProduced() {
        int modulo = currentTurn % resourceTimeToTurn;
        return resourceTimeToTurn - modulo;
    }

    public abstract Unit produceUnit();

    public abstract Resource produceResource();

    public abstract String getProducedUnitName();

    public abstract String getProducedResourceName();

    public int getResourceAmount() {
        return resourceAmount;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }
}