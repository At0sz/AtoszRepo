package settlersOOPCheckpoint.main.java.hu.progmasters.settlers.building;

import settlersOOPCheckpoint.main.java.hu.progmasters.settlers.resource.Resource;
import settlersOOPCheckpoint.main.java.hu.progmasters.settlers.resource.ResourceType;
import settlersOOPCheckpoint.main.java.hu.progmasters.settlers.unit.Archer;
import settlersOOPCheckpoint.main.java.hu.progmasters.settlers.unit.Unit;

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
