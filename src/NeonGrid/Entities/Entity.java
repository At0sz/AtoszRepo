package NeonGrid.Entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    private double budget = 0;
    private double baseNetworkCost;
    private List<Devices> devices = new ArrayList<>();

    public abstract void addIncome();

    public double calculateTotalCost(){
        return this.baseNetworkCost + devices.stream()
                .mapToDouble(Devices::getEnergyCost)
                .sum();
    }

    public void isBroke(){
        setBudget(getBudget() - calculateTotalCost());
    }

    public boolean canPayBills(){
        return budget <= 0;
    }

    public void addDevice(Devices device){
        devices.add(device);
    }

    public double getBaseNetworkCost() {
        return baseNetworkCost;
    }

    public void setBaseNetworkCost(double baseNetworkCost) {
        this.baseNetworkCost = baseNetworkCost;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<Devices> getDevices() {
        return devices;
    }

    public void setDevices(List<Devices> devices) {
        this.devices = devices;
    }
}
