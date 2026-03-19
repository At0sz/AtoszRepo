package Galactic_Trader;

public class CargoShip extends SpaceShip {

    public static final int TRAVEL_COST2 = 50;

    public CargoShip(String model, int cargoCapacity) {
        super(model, cargoCapacity);


        if (cargoCapacity < 2000) {
            setCargoCapacity(2000);
        }
    }

    @Override
    public void travel(Planet targetPlanet) {
        int travelCost = TRAVEL_COST2;

        if (getCurrentFuel() >= travelCost) {
            if (targetPlanet.isInWar() && getPilot().getExperience() < 50) {
                System.out.println("-------------------------------------");
                System.out.println("--------HIBA! AI FELÜLBÍRÁLÁS--------");
                System.out.println("-------------------------------------");
                System.out.println("Hajó analízis alapján az utazás túl magas kockázati tényezőkkel rendelkezik");
                System.out.println("Ez alapján az utazás felfüggesztve ❌");
            } else {
                setCurrentFuel(getCurrentFuel()- travelCost);
                System.out.println("----------------------------------");
                System.out.println("Welcome to " + targetPlanet.getPlanetName());
                System.out.println("----------------------------------");

            }

        } else {
            System.out.println("Nincs elegendő üzemanyag az utazás megkezdéséhez ⛔🆘");
        }


    }


}

