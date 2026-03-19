package Galactic_Trader;

public class ScoutShip extends SpaceShip {

    public static final int TRAVEL_COST1 = 10;

    public ScoutShip(String model, int cargoCapacity) {
        super(model, cargoCapacity);
    }

    @Override
    public void travel(Planet targetPlanet) {
        int travelCost = TRAVEL_COST1;

        if (getCurrentFuel() >= travelCost) {

            if (targetPlanet.isInWar() && getPilot().getExperience() < 30) {
                System.out.println("Scout hiba: Túl veszélyes még nekünk is!");
            } else {
                setCurrentFuel(getCurrentFuel() - travelCost);
                System.out.println("Scout üdvözlet a(z) " + targetPlanet.getPlanetName() + " bolygón!");
            }
        } else {
            System.out.println("Nincs elég nafta a felderítéshez!");
        }
    }
}
