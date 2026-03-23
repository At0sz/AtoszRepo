package IntergalacticTradingAndLogisticalCenter.Cargos;

import java.time.LocalDate;

public class Technology extends Cargo {
    private int energyConsumption;

    public Technology(String id, String name, double weight, double baseValue, boolean isFragile, LocalDate expirationDate, int energyConsumption) {
        super(id, name, weight, baseValue, isFragile, expirationDate);
        this.energyConsumption = energyConsumption;
    }

    @Override
    public String getCategory() {
        return "TECHNOLOGY";
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }
    @Override
    public double getBaseValue() {
        return isFragile() ? super.getBaseValue() * 2 : super.getBaseValue();
    }
}
