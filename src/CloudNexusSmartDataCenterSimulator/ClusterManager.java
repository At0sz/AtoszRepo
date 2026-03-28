package CloudNexusSmartDataCenterSimulator;

import CloudNexusSmartDataCenterSimulator.ExceptionAndInterface.BootFailureException;
import CloudNexusSmartDataCenterSimulator.ServerCore.Node;
import CloudNexusSmartDataCenterSimulator.ServerCore.ServerNode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
public class ClusterManager {
    private List<Node> cluster = new ArrayList<>();

    public ClusterManager(List<Node> cluster) {
        this.cluster = cluster;
    }

    public void addNode(Node node) {
        if (this.cluster.contains(node)) {
            throw new IllegalArgumentException("Node already exists ❌❌❌");
        } else {
            this.cluster.add(node);
            System.out.println("Successfully added node");
        }
    }

    // STREAM FELADAT 1: Add vissza az összes ONLINE állapotú ServerNode-ot!
    // Tipp: filter(instanceof) és castolás kellhet.
    public List<ServerNode> getOnlineServers() {
        return cluster.stream()
                .filter(Node::isOnline)
                .filter(p -> p instanceof ServerNode)
                .map(p -> (ServerNode) p)
                .toList();

    }

    // STREAM FELADAT 2: Számold ki a clusterben lévő ServerNode-ok összfogyasztását!
    public double calculateTotalPowerUsage() {
        return cluster.stream()
                .filter(p -> p instanceof ServerNode)
                .map(p -> (ServerNode) p)
                .mapToDouble(ServerNode::getPowerUsage)
                .sum();
    }

    // STREAM FELADAT 3: Keresd meg azt a Node-ot, aminek a leghosszabb a nodeId-ja!
    public Node getNodeWithLongestId() {
        return cluster.stream()
                .max(Comparator.comparingInt(p -> p.getNodeId().length()))
                .orElse(null);
    }

    // KIVÉTELKEZELÉS FELADAT: Próbálj meg minden ServerNode-ot elindítani!
    public void bootAllServers() {
        cluster.stream()
                .filter(p -> p instanceof ServerNode)
                .map(p -> (ServerNode) p)
                .forEach(serverNode -> {
                    try {
                        serverNode.boot();
                    } catch (BootFailureException e) {
                        System.out.println("Hiba a szerver indításakor: " + e.getMessage());
                    }
                });
    }
}


