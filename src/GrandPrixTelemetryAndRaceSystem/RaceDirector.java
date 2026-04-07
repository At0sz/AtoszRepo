package GrandPrixTelemetryAndRaceSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RaceDirector {
    private List<RaceVehicle> roster = new ArrayList<>();

    public void registerVehicle(RaceVehicle vehicle) {
        roster.add(vehicle);
    }

    public List<RaceVehicle> getOperationalVehiclesSortedBySpeed(){
        return roster.stream()
                .filter(car->car.durability > 0)
                .sorted(Comparator.comparingDouble(RaceVehicle::calculateActualSpeed).reversed())
                .toList();
    }

    public Map<TireType,List<RaceVehicle>> groupVehiclesBytire(){
        return roster.stream()
                .collect(Collectors.groupingBy(RaceVehicle::getCurrentTire));
    }

    public double getTopSpeedOfAllTime(){
        return roster.stream()
                .mapToDouble(RaceVehicle::calculateActualSpeed)
                .max().orElse(0.0);
    }

    public String getPilotNamesInDangerZone(){
        return roster.stream()
                .filter(pilot -> pilot.durability <= 30)
                .map(RaceVehicle::getPilotName)
                .collect(Collectors.joining(", "));
    }

}
