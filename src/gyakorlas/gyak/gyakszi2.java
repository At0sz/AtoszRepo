package gyakorlas.gyak;

import java.util.*;

class Packet {
    String protocol;
    int size;

    Packet(String protocol, int size) {
        this.protocol = protocol;
        this.size = size;
    }
}

public class gyakszi2 {
    public static void main(String[] args) {
        List<Packet> packets = Arrays.asList(
                new Packet("TCP", 1500),
                new Packet("UDP", 500),
                new Packet("TCP", 1200),
                new Packet("ICMP", 64),
                new Packet("TCP", 800)
        );

        // FELADAT:
        // 1. Indíts egy streamet a 'packets' listán!
        // 2. Szűrj rá (filter) a "TCP" protokollra!
        // 3. Alakítsd át (map) a streamet csak a méretekre (size)!
        //    Tipp: Használd a .mapToInt(p -> p.size) metódust, mert azon van sum()!
        // 4. Számold ki az összeget a .sum() metódussal!

        int totalTcpSize = packets.stream()
                .filter(p -> p.protocol.equals("TCP"))
                .mapToInt(p -> p.size)
                .sum();

        OptionalInt maxSize = packets.stream()
                .mapToInt(p -> p.size)
                .max();

        System.out.println("A TCP forgalom összesen: " + totalTcpSize + " bytes");
        System.out.println("A legnagyobb pedig: " + maxSize.orElse(0));
    }
}
