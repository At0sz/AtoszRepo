package NeonGrid.Entities;



public class SoloRunner extends Entity {
    private double salary;

    public SoloRunner(double bounty) {
        this.salary = bounty;
        setBaseNetworkCost(15);
        addDevice(Devices.SERVER);
        addDevice(Devices.TERMINAL);
    }

    @Override
    public void addIncome() {
        setBudget(getBudget() + salary);
    }

    public double getSalary() {
        return salary;
    }

}
