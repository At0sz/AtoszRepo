package SpaceMissionControlSystem.Astronaut;

import SpaceMissionControlSystem.Enums.Action;
import SpaceMissionControlSystem.Mission;

public abstract class Astronaut {
    private static int idCounter = 1;
    private int id;
    private String name;
    Mission mission;

    public Astronaut(String name) {
        this.name = name;
        this.id = idCounter++;
    }

    public abstract Action performAction();

    public int getId() {
        return id;
    }

    public Mission getMission() {
        return mission;
    }

    public String getName() {
        return name;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + this.id +"("+ this.name+")";
    }
}
