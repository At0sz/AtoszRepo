package Galactic_Trader;

public class Planet {
    public static final int CONTRACT_PRICE = 200;
    private String planetName;
    private boolean isInWar;
    private int fuelPrice;
    Pilot governor;

    public Planet(String planetName, int fuelPrice, Pilot governor) {
        this.planetName = planetName;
        this.fuelPrice = fuelPrice;
        this.governor = governor;
    }

    public String tradingContract(Planet otherPlanet) {
        int contractPrice = CONTRACT_PRICE;

        if (!this.isInWar && !otherPlanet.isInWar) {
            if (governor.getCredits() >= contractPrice) {


                governor.setCredits(governor.getCredits() - contractPrice);
                governor.setExperience(governor.getExperience() + 10);
                otherPlanet.governor.setExperience(otherPlanet.governor.getExperience() + 10);
                return "Sikeres szerződés kötés ✅\nAktuális vagyon " + governor.getCredits();
            } else
                return "Nincs elegendő összeg, szükséges összeg " + contractPrice + " ,aktuális vagyon: " + governor.getCredits();
        } else return "Háború van nem lehetséges a kereskedelmi szerződés ilyen feltételek mellet";

    }


    public int getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(int fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    public Pilot getGovernor() {
        return governor;
    }

    public void setGovernor(Pilot governor) {
        this.governor = governor;
    }

    public boolean isInWar() {
        return isInWar;
    }

    public void setInWar(boolean inWar) {
        isInWar = inWar;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }
}
