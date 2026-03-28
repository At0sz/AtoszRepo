package CloudNexusSmartDataCenterSimulator.DataPacket;

import lombok.Getter;

@Getter
public abstract class DataPacket {
    private String payload;
    private int sizeMb;

    public DataPacket(String payload, int sizeMb) {
        this.payload = payload;
        this.sizeMb = sizeMb;
    }


}
