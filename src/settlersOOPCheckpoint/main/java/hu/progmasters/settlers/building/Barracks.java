package settlersOOPCheckpoint.main.java.hu.progmasters.settlers.building;


import settlersOOPCheckpoint.main.java.hu.progmasters.settlers.resource.Resource;
import settlersOOPCheckpoint.main.java.hu.progmasters.settlers.resource.ResourceType;
import settlersOOPCheckpoint.main.java.hu.progmasters.settlers.unit.Swordsman;
import settlersOOPCheckpoint.main.java.hu.progmasters.settlers.unit.Unit;

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

