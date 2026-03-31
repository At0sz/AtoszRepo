package NeonGrid.Entities;

public class DevSyndicate extends Entity{
    private double salaryOne;
    private double salaryTwo;

    public DevSyndicate(double budgetOne, double budgetTwo) {
        this.salaryOne = budgetOne;
        this.salaryTwo = budgetTwo;
        setBaseNetworkCost(25);
        addDevice(Devices.SERVER);
        addDevice(Devices.SERVER);
        addDevice(Devices.TERMINAL);
        addDevice(Devices.TERMINAL);
        addDevice(Devices.COFFEMAKER);
    }


    public double getSalaryOne() {
        return salaryOne;
    }

    public double getSalaryTwo() {
        return salaryTwo;
    }

    @Override
    public void addIncome() {
    setBudget(this.getSalaryOne() + this.getSalaryTwo() + getBudget());
    }

}
