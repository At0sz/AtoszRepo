package NeonGrid;

public class RogueAI extends Entity {
    private double salary;


    public RogueAI(double budget) {
        this.salary = budget;
        setBaseNetworkCost(5);
        addDevice(Devices.CLOUD);
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public void addIncome() {
        setBudget(getBudget() + getSalary());
    }
}
