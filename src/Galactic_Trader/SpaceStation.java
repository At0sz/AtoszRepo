package Galactic_Trader;

public class SpaceStation {

    public void upGradeShip(SpaceShip ship){
        if(ship.getPilot().getCredits() >= 500){
            ship.setCargoCapacity((int) (ship.getCargoCapacity() * 1.2));
            ship.getPilot().setCredits(ship.getPilot().getCredits() - 500);
        }
    }
}
