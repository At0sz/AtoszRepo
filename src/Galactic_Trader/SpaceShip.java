package Galactic_Trader;

public class SpaceShip {
    public static final int TRAVEL_COST = 30;
    private String model;
    private int cargoCapacity;
    private int currentFuel;
    private Pilot pilot;

    public SpaceShip(String model, int cargoCapacity) {
        this.model = model;
        this.cargoCapacity = cargoCapacity;
        this.currentFuel = 100;
    }

    public void refuel(Planet planet, int amount) {
        int price = amount * planet.getFuelPrice();

        if (pilot == null) throw new IllegalStateException("Nincs pilóta a hajón!");

        if (this.pilot.getCredits() >= price) {
            this.pilot.setCredits(this.pilot.getCredits() - price);
            this.currentFuel += amount;
            System.out.println("Sikeres vásárlás " + price + " értékben, üzemanyag kiadva : " + amount + " Lt ✅");
        } else {
            System.out.println("Sikertelen vásárlás, fizetendő összeg: " + price + " ,elérhető keret: " + this.pilot.getCredits());
        }
    }

    public void travel(Planet targetPlanet) {
        int travelCost = TRAVEL_COST;

        if (this.currentFuel >= travelCost) {
            if (targetPlanet.isInWar() && this.pilot.getExperience() < 50) {
                System.out.println("-------------------------------------");
                System.out.println("--------HIBA! AI FELÜLBÍRÁLÁS--------");
                System.out.println("-------------------------------------");
                System.out.println("Hajó analízis alapján az utazás túl magas kockázati tényezőkkel rendelkezik");
                System.out.println("Ez alapján az utazás felfüggesztve ❌");
            } else {
                this.currentFuel -= travelCost;
                System.out.println("----------------------------------");
                System.out.println("Welcome to " + targetPlanet.getPlanetName());
                System.out.println("----------------------------------");

            }

        } else {
            System.out.println("Nincs elegendő üzemanyag az utazás megkezdéséhez ⛔🆘");
        }


    }


    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public int getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(int currentFuel) {
        this.currentFuel = currentFuel;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }
}
