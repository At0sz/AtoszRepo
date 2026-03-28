🏛️ A Projekt Architektúrája (Specifikáció)
1. A Kivételek és Interfészek (Az alapok)
   BootFailureException (Checked Exception)

Ősosztály: Exception

Konstruktor: Fogadjon egy String message paramétert.

OverloadException (Unchecked Exception)

Ősosztály: RuntimeException

Konstruktor: Fogadjon egy String message paramétert.

Bootable (Interfész)

Metódus: void boot() throws BootFailureException;

2. Az Adatcsomagok (A rakomány)
   DataPacket (Absztrakt osztály)

Attribútumok: String payload (az adat), int sizeMb (méret).

Konstruktor: Mindkét attribútumot állítsa be.

Metódusok: Getterek mindkettőhöz.

LogPacket (Concrete osztály)

Ősosztály: DataPacket

Attribútum: String errorLevel (pl. "INFO", "WARN", "CRITICAL").

Konstruktor: payload, sizeMb, errorLevel. Getterek.

3. A Szerver Csomópontok (A Core)
   Node (Absztrakt osztály)

Attribútumok: String nodeId, boolean isOnline (alapértelmezetten false).

Konstruktor: Csak a nodeId-t kérje be.

Metódusok: Getterek/Setterek. Absztrakt metódus: void displayStatus();

ServerNode (Concrete osztály)

Ősosztály: Node

Megvalósítja: Bootable interfész.

Attribútumok: int cpuCores, double powerUsage.

Implementáció (boot): Ha a powerUsage nagyobb, mint 1000.0, dobjon BootFailureException-t. Különben állítsa az isOnline-t true-ra.

DatabaseNode<T extends DataPacket> (Generikus Concrete osztály)

Ősosztály: Node

Attribútumok: List<T> storage (üres lista induláskor), int maxStorageMb.

Metódus (saveData): Fogadjon egy T packet-et. Ha a storage jelenlegi elemeinek összmérte (Stream!) + a packet mérete > maxStorageMb, dobjon OverloadException-t. Különben adja hozzá a listához.

4. A Motor (Stream API és Logika)
   ClusterManager (A fő vezérlő)

Attribútum: List<Node> cluster (üres lista).

Metódus: void addNode(Node node) - Hozzáadja a csomópontot a listához.











💻 A Kódvázlatod (Ezt másold be, és töltsd ki a TODO-kat!)
Itt a teljes struktúra, amit a specifikáció alapján vázoltam fel. A te feladatod a "hús" felrakása a csontvázra!

Java
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// --- 1. KIVÉTELEK ÉS INTERFÉSZEK ---
class BootFailureException extends Exception {
// TODO: Konstruktor megírása
}

class OverloadException extends RuntimeException {
// TODO: Konstruktor megírása
}

interface Bootable {
void boot() throws BootFailureException;
}

// --- 2. ADATCSOMAGOK ---
abstract class DataPacket {
// TODO: Attribútumok (payload, sizeMb), konstruktor, getterek
}

class LogPacket extends DataPacket {
// TODO: errorLevel attribútum, konstruktor hívja a super()-t, getter
}

// --- 3. CSOMÓPONTOK (NODES) ---
abstract class Node {
private String nodeId;
private boolean isOnline = false;

    // TODO: Konstruktor (nodeId), getterek/setterek, absztrakt displayStatus()
}

class ServerNode extends Node implements Bootable {
private int cpuCores;
private double powerUsage;

    // TODO: Konstruktor

    @Override
    public void boot() throws BootFailureException {
        // TODO: Ha powerUsage > 1000.0 -> Exception. Különben isOnline = true.
    }

    @Override
    public void displayStatus() {
        // TODO: Írja ki az azonosítót és hogy online-e.
    }
}

class DatabaseNode<T extends DataPacket> extends Node {
private List<T> storage = new ArrayList<>();
private int maxStorageMb;

    // TODO: Konstruktor

    public void saveData(T packet) {
        // TODO: Stream-mel add össze a storage-ben lévő csomagok sizeMb értékét!
        // TODO: Ha túllépi a maxStorageMb-t -> throw OverloadException.
        // TODO: Ha befér, add hozzá a storage listához.
    }

    @Override
    public void displayStatus() {
        // TODO: Írja ki a node állapotát és a tárolt csomagok számát.
    }
}

// --- 4. A VEZÉRLŐ (A TE STREAM FELADATAID) ---
class ClusterManager {
private List<Node> cluster = new ArrayList<>();

    public void addNode(Node node) { cluster.add(node); }

    // STREAM FELADAT 1: Add vissza az összes ONLINE állapotú ServerNode-ot!
    // Tipp: filter(instanceof) és castolás kellhet.
    public List<ServerNode> getOnlineServers() {
        return null; // TODO: Implementáld Stream-mel!
    }

    // STREAM FELADAT 2: Számold ki a clusterben lévő ServerNode-ok összfogyasztását!
    public double calculateTotalPowerUsage() {
        return 0.0; // TODO: Implementáld Stream-mel!
    }

    // STREAM FELADAT 3: Keresd meg azt a Node-ot, aminek a leghosszabb a nodeId-ja!
    public Node getNodeWithLongestId() {
        return null; // TODO: Implementáld Stream-mel (max)!
    }

    // KIVÉTELKEZELÉS FELADAT: Próbálj meg minden ServerNode-ot elindítani!
    public void bootAllServers() {
        // TODO: Menj végig a clusteren. Ha az elem ServerNode, hívd meg a boot()-ot.
        // TODO: Tedd try-catch blokkba, és ha elszáll, írd ki a konzolra a hibaüzenetet!
    }
}