package CloudNexusSmartDataCenterSimulator.ServerCore;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Node {
    private String nodeId;
    @Setter
    private boolean isOnline;

    public Node(String nodeId) {
        this.nodeId = nodeId;
    }



    public abstract void displayStatus();
}
