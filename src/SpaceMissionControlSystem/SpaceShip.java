package SpaceMissionControlSystem;

import SpaceMissionControlSystem.Astronaut.Astronaut;
import SpaceMissionControlSystem.Astronaut.Engineer;
import SpaceMissionControlSystem.Astronaut.Pilot;
import SpaceMissionControlSystem.Astronaut.Scientist;
import SpaceMissionControlSystem.Enums.Role;

import java.util.List;
import java.util.Map;

public class SpaceShip {
    public static void main(String[] args) {

        Mission mars = new Mission();

        Astronaut pilot = new Pilot("Jani");
        Astronaut engineer = new Engineer("Béla");
        Astronaut scientist = new Scientist("Ákos");

        mars.addAstronaut(pilot);
        mars.addAstronaut(engineer);
        mars.addAstronaut(scientist);

        mars.startMission();

        mars.processRound();
        System.out.println();
        mars.processRound();
        System.out.println();
        mars.processRound();
        System.out.println();
        mars.processRound();
        System.out.println();

        List<Astronaut> sortedList = mars.getAstronautsSorted();
        sortedList.forEach(System.out::println);
        System.out.println();

        List<Astronaut> engineerList = mars.getEngineers();
        engineerList.forEach(System.out::println);
        System.out.println();

        Map<Role,Long> astronautsByRole = mars.getAstronautsByRole();
        astronautsByRole.forEach((role, count) -> System.out.println(role + " = " + count));


    }
}
