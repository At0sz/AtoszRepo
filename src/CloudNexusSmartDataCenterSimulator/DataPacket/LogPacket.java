package CloudNexusSmartDataCenterSimulator.DataPacket;

import lombok.Getter;

@Getter
public class LogPacket extends DataPacket {
    private String errorLevel;

    public LogPacket(String payload, int sizeMb, String errorLevel) {
        super(payload, sizeMb);
        this.errorLevel = errorLevel;
    }
}
