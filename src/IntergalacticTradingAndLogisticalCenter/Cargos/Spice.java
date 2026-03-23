package IntergalacticTradingAndLogisticalCenter.Cargos;

import java.time.LocalDate;

public class Spice extends Cargo {
    private double purity;

    public Spice(String id, String name, double weight, double baseValue, boolean isFragile, LocalDate expirationDate, double purity) {
        super(id, name, weight, baseValue, isFragile, expirationDate);
        this.purity = purity;
    }

    @Override
    public double getBaseValue() {
        return super.getBaseValue() * purity;
    }

    @Override
    public String getCategory() {
        return "SPICE";
    }

    public double getPurity() {
        return purity;
    }
}
