package CloudNexusSmartDataCenterSimulator.ServerCore;

import CloudNexusSmartDataCenterSimulator.DataPacket.DataPacket;
import CloudNexusSmartDataCenterSimulator.ExceptionAndInterface.OverloadException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DatabaseNode<T extends DataPacket> extends Node {
    private List<T> storage = new ArrayList<>();
    private int maxStorageMb;

    public DatabaseNode(String nodeId, List<T> storage, int maxStorageMb) {
        super(nodeId);
        this.storage = storage;
        this.maxStorageMb = maxStorageMb;
    }

    public void saveData(T packet){
        int sumStorageMb = storage.stream()
                .mapToInt(DataPacket::getSizeMb)
                .sum();

        if(packet.getSizeMb()+ sumStorageMb > maxStorageMb){
            throw new OverloadException("Packet overflow ❌❌❌");
        }else {
            storage.add(packet);
            System.out.println("Successfully saved packet");
        }
    }

    @Override
    public void displayStatus() {
        System.out.println(this.getNodeId() + " (Database) - Tárolt csomagok: " + storage.size());
    }
}
