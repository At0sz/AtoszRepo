package CloudNexusSmartDataCenterSimulator;

import CloudNexusSmartDataCenterSimulator.DataPacket.LogPacket;
import CloudNexusSmartDataCenterSimulator.ServerCore.DatabaseNode;
import CloudNexusSmartDataCenterSimulator.ServerCore.ServerNode;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 1. Klaszter központ létrehozása
        ClusterManager manager = new ClusterManager(new ArrayList<>());

        // 2. Szerverek és Adatbázisok példányosítása
        ServerNode s1 = new ServerNode("SRV-ALPHA", 16, 850.5);
        ServerNode s2 = new ServerNode("SRV-BETA-MASSIVE", 32, 1200.0); // Ez el fog szállni a bootnál!
        DatabaseNode<LogPacket> db1 = new DatabaseNode<>("DB-LOGS-01", new ArrayList<>(), 500);

        // 3. Hozzáadás a klaszterhez
        manager.addNode(s1);
        manager.addNode(s2);
        manager.addNode(db1);

        System.out.println("\n--- BOOT SZEKVENCIA INDÍTÁSA ---");
        manager.bootAllServers(); // S2 dobni fogja a hibát a konzolra

        System.out.println("\n--- ADATBÁZIS TESZT ---");
        LogPacket log1 = new LogPacket("System Check OK", 200, "INFO");
        LogPacket log2 = new LogPacket("Memory Leak Detected", 400, "CRITICAL");

        db1.saveData(log1); // Ez sikeres lesz
        try {
            db1.saveData(log2); // Ez túllépi az 500MB-t, OverloadException!
        } catch (Exception e) {
            System.out.println("Adatmentési hiba: " + e.getMessage());
        }

        System.out.println("\n--- STREAM EREDMÉNYEK ---");
        System.out.println("Online szerverek száma: " + manager.getOnlineServers().size());
        System.out.println("Összfogyasztás: " + manager.calculateTotalPowerUsage() + " W");
        System.out.println("Leghosszabb nevű Node: " + manager.getNodeWithLongestId().getNodeId());
    }
}
