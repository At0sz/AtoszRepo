package IntergalacticTradingAndLogisticalCenter.Cargos;

import java.time.LocalDate;

public class AlienArtifact extends Cargo{
    private int dangerLevel;

    public AlienArtifact(String id, String name, double weight, double baseValue, boolean isFragile, LocalDate expirationDate, int dangerLevel) {
        super(id, name, weight, baseValue, isFragile, expirationDate);
        this.dangerLevel = dangerLevel;
    }

    @Override
    public String getCategory() {
        return "ALIENARTIFACT";
    }

    public int getDangerLevel() {
        return dangerLevel;
    }
}
