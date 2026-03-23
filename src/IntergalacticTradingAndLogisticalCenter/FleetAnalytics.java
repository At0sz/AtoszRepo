package IntergalacticTradingAndLogisticalCenter;

import IntergalacticTradingAndLogisticalCenter.Cargos.AlienArtifact;
import IntergalacticTradingAndLogisticalCenter.Cargos.Cargo;
import IntergalacticTradingAndLogisticalCenter.Cargos.Tradable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FleetAnalytics {
    private List<Freighter> fleet = new ArrayList<>();

    public Map<String, Double> getTotalValueByCategory(){
        return fleet.stream()
                .flatMap(p->p.getInventory().stream())
                .collect(Collectors.groupingBy(
                        p-> p.getCategory(),
                        Collectors.summingDouble(Tradable::getBaseValue)
                ));
    }

    public List<Cargo> getFragileExpiringSoon(LocalDate date) {
        return fleet.stream()
                .flatMap(f -> f.getInventory().stream())
                .filter(Cargo.class::isInstance) // Csak a Cargo típusúaknak van dátuma
                .map(p -> (Cargo) p)             // Castoljuk át
                .filter(c -> c.isFragile() && c.getExpirationDate().isBefore(date))
                .sorted(Comparator.comparing(Cargo::getExpirationDate))
                .collect(Collectors.toList());
    }

    public void getMostValuableFreighter() {
        fleet.stream()
                .max(Comparator.comparingDouble(f -> f.getInventory().stream()
                        .mapToDouble(Tradable::getBaseValue).sum()))
                .ifPresent(f -> System.out.println("Legértékesebb hajó: " + f.getShipName()));
    }

    public void getAverageDangerLevel(){
        System.out.println("\n--------------------");
        System.out.println("Average Danger Level");
        fleet.stream()
                .flatMap(p->p.getInventory().stream())
                .filter(p-> p instanceof AlienArtifact)
                .mapToInt(p->((AlienArtifact)p).getDangerLevel())
                .average().ifPresent(System.out::println);
        System.out.println("--------------------");
    }

    public List<Freighter> getFleet() {
        return fleet;
    }
}
