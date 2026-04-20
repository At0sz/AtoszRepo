package settlersOOPCheckpoint.building;

import settlersOOPCheckpoint.resource.Resource;
import settlersOOPCheckpoint.resource.ResourceType;
import settlersOOPCheckpoint.unit.Archer;
import settlersOOPCheckpoint.unit.Unit;

public class Archery extends Building {

    public Archery() {
        super(3, 2, 5);
    }

    @Override
    public Unit produceUnit() {
        return new Archer();
    }

    @Override
    public Resource produceResource() {
        return new Resource(ResourceType.GOLD, getResourceAmount());
    }

    @Override
    public String getProducedUnitName() {
        return "Archer";
    }

    @Override
    public String getProducedResourceName() {
        return "Gold";
    }
}
