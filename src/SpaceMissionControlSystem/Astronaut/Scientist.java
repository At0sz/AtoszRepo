package SpaceMissionControlSystem.Astronaut;

import SpaceMissionControlSystem.Enums.Action;

public class Scientist extends Astronaut{
    public Scientist(String name) {
        super(name);
    }

    @Override
    public Action performAction() {
        if (getMission().getCurrentRound() % 3 == 0)
        {
            return Action.RESEARCH;
        }else{
            return Action.REPAIR;
        }
    }
}
