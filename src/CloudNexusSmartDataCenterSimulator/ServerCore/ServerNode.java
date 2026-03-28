package CloudNexusSmartDataCenterSimulator.ServerCore;

import CloudNexusSmartDataCenterSimulator.ExceptionAndInterface.BootFailureException;
import CloudNexusSmartDataCenterSimulator.ExceptionAndInterface.Bootable;
import lombok.Getter;

@Getter
public class ServerNode extends Node implements Bootable {
    private int cpuCores;
    private double powerUsage;

    public ServerNode(String nodeId, int cpuCores, double powerUsage) {
        super(nodeId);
        this.cpuCores = cpuCores;
        this.powerUsage = powerUsage;
    }

    @Override
    public void displayStatus() {
        System.out.println(this.getNodeId() + " is " + (this.isOnline()?"online":"offline"));
    }

    @Override
    public void boot() throws BootFailureException {
        if (powerUsage > 1000.0){
            throw new BootFailureException("Too much power usage: " + powerUsage + " ❌❌❌");
        }else {
              this.setOnline(true);
        }
    }

    public int getCpuCores() {
        return cpuCores;
    }

    public double getPowerUsage() {
        return this.powerUsage;
    }
}
