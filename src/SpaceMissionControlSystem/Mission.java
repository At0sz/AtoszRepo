package SpaceMissionControlSystem;

import SpaceMissionControlSystem.Astronaut.Astronaut;
import SpaceMissionControlSystem.Astronaut.Engineer;
import SpaceMissionControlSystem.Enums.Role;
import SpaceMissionControlSystem.Exception.NoAstronautException;

import java.util.*;
import java.util.stream.Collectors;

public class Mission {
    public final int MAX_ASTRONAUT = 5;
    private String missionName;
    private int currentRound = 0;
    private List<Astronaut> astronauts = new ArrayList<>();

    public void addAstronaut(Astronaut astronaut) {
        if (astronauts.size() < MAX_ASTRONAUT) {
            astronauts.add(astronaut);
            astronaut.setMission(this);
        }
    }

    public void startMission() {
        this.currentRound = 1;
    }

    public void processRound() {
        if (astronauts.isEmpty()) {
            throw new NoAstronautException("There is no Astronauts to process!");
        }
        for (Astronaut a : astronauts) {
            System.out.println(a.getClass().getSimpleName() + " " + a.getId() + " performs " + a.performAction());
        }
        System.out.println("Round finished: " + currentRound);
        currentRound++;
    }

    public Map<Role, Long> getAstronautsByRole() {
    return astronauts.stream()
            .collect(Collectors.groupingBy(
                a -> Role.valueOf(a.getClass().getSimpleName().toUpperCase()),
                Collectors.counting()
            ));
}

    public List<Astronaut> getEngineers() {
        return astronauts.stream()
                .filter(p->p instanceof Engineer)
                .collect(Collectors.toList());
    }

    public List<Astronaut> getAstronautsSorted(){
        return astronauts.stream()
                .sorted(Comparator.comparingInt(Astronaut::getId))
                .collect(Collectors.toList());

    }


    public List<Astronaut> getAstronauts() {
        return astronauts;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public String getMissionName() {
        return missionName;
    }
}
