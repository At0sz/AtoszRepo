package SpaceMissionControlSystem.Astronaut;

import SpaceMissionControlSystem.Enums.Action;

public class Pilot extends Astronaut{

    public Pilot(String name) {
        super(name);
    }

    @Override
    public Action performAction() {
        return Action.FLY;
    }


}
