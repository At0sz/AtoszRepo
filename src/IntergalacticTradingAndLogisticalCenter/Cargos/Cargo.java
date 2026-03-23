package IntergalacticTradingAndLogisticalCenter.Cargos;

import java.time.LocalDate;

public abstract class Cargo implements Tradable {
    private String id;
    private String name;
    private double weight;
    private double baseValue;
    private boolean isFragile;
    private LocalDate expirationDate;

    public Cargo(String id, String name, double weight, double baseValue, boolean isFragile, LocalDate expirationDate) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.baseValue = baseValue;
        this.isFragile = isFragile;
        this.expirationDate = expirationDate;
    }

    // A Tradable interfész metódusainak alapértelmezett megvalósítása
    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    // Getters a többi mezőhöz (szükség lesz rájuk a Stream-eknél)
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isFragile() {
        return isFragile;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    // Ezt minden gyereknek kötelező lesz megvalósítania (Spice -> "SPICE", etc.)
    @Override
    public abstract String getCategory();
}