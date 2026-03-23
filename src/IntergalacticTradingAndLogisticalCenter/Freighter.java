package IntergalacticTradingAndLogisticalCenter;

import IntergalacticTradingAndLogisticalCenter.Cargos.AlienArtifact;
import IntergalacticTradingAndLogisticalCenter.Cargos.Tradable;
import IntergalacticTradingAndLogisticalCenter.Exceptions.CargoFullException;
import IntergalacticTradingAndLogisticalCenter.Exceptions.IllegalCargoException;

import java.util.ArrayList;
import java.util.List;

public class Freighter {
    private String shipName;
    private double maxWeightCapacity;
    private List<Tradable> inventory = new ArrayList<>();

    public Freighter(String shipName, double maxWeightCapacity) {
        this.shipName = shipName;
        this.maxWeightCapacity = maxWeightCapacity;
    }

    // --- 1. Feladat: loadCargo ---
    // Tipp: Számold ki a jelenlegi összsúlyt a listában lévő elemek súlyának összegzésével,
    // és nézd meg, befér-e még az új 'item'. Ha nem, dobd a CargoFullException-t!
    public void loadCargo(Tradable item) throws CargoFullException {
        int weight = inventory.stream()
                .mapToInt(p -> (int) p.getWeight())
                .sum();
        if (item.getWeight() + weight > maxWeightCapacity) {
            throw new CargoFullException("Cannot load because we just exceed the weight capacity");
        } else {
            inventory.add(item);
        }
    }

    // --- 2. Feladat: securityCheck ---
    // Tipp: Menj végig az inventory-n egy Stream-mel vagy for-each-el.
    // Csak akkor tudod megnézni a dangerLevel-t, ha az elem 'instanceof AlienArtifact'.
    // Ha találsz olyat, ami > 8, dobd az IllegalCargoException-t!
    public void securityCheck() throws IllegalCargoException {
        System.out.println("----------------------");
        System.out.println("Security Check Started");
        System.out.println("----------------------");
        int maxSecurityLevel = inventory.stream()
                .filter(p-> p instanceof AlienArtifact)
                .mapToInt(p->((AlienArtifact) p).getDangerLevel())
                .max()
                .orElse(0);
        if(maxSecurityLevel > 8){
            throw new IllegalCargoException("Cannot start your mission because you just exceed the security level ( !LVL8! )");
        }else{
            System.out.println("Security Check Ended ✅\nYou can start your mission!");
        }
    }

    // Getterek a Stream API feladatokhoz (FleetAnalytics)
    public List<Tradable> getInventory() {
        return inventory;
    }

    public String getShipName() {
        return shipName;
    }
}
