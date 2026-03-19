package SpaceMissionControlSystem.Astronaut;

import SpaceMissionControlSystem.Enums.Action;

public class Engineer extends Astronaut {
    public Engineer(String name) {
        super(name);
    }

    @Override
    public Action performAction() {
        if (getMission().getCurrentRound() % 2 == 0) {
            return Action.REPAIR;
        }else{
            return Action.FLY;
        }
    }
}
