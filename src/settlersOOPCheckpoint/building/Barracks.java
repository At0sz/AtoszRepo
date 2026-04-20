package settlersOOPCheckpoint.building;


import settlersOOPCheckpoint.resource.Resource;
import settlersOOPCheckpoint.resource.ResourceType;
import settlersOOPCheckpoint.unit.Swordsman;
import settlersOOPCheckpoint.unit.Unit;

public class Barracks extends Building {

    public Barracks() {
        super(4, 3, 10);
    }

    @Override
    public Unit produceUnit() {
        return new Swordsman();
    }

    @Override
    public Resource produceResource() {
        return new Resource(ResourceType.STEEL, getResourceAmount());
    }

    @Override
    public String getProducedUnitName() {
        return "Swordsman";
    }

    @Override
    public String getProducedResourceName() {
        return "Steel";
    }
}

