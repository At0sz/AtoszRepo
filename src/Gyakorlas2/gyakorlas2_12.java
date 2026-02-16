package Gyakorlas2;

import java.util.ArrayList;
import java.util.List;

public class gyakorlas2_12 {
    public static void main(String[] args) {

        // 1. Hozz létre egy listát Integer típusokkal!
        List<Integer> scanResults = new ArrayList<>();

        // 2. Adj hozzá portokat (használd a String Manipulation-t, ha sok van!)
        // Tipp: 22, 80, 443, 3389, 8080
        scanResults.add(22);
        scanResults.add(80);
        scanResults.add(443);
        scanResults.add(3389);
        scanResults.add(8080);
        // ... a többit add hozzá te!
        scanResults.forEach(p -> System.out.println("Vizsgált port: " + p));

        System.out.println("\n--- Port Vizsgálat Indul ---");

        // 3. Menj végig a listán egy ciklussal
        for (Integer port : scanResults) {
            // Itt jön a mágia:
            // Ha a port 22 -> írd ki: "SSH - Figyelj rá!"
            // Ha a port 443 -> írd ki: "HTTPS - Biztonságos"
            // Ha a port 3389 -> írd ki: "RDP - Távoli asztal nyitva!"
            // Minden másra -> írd ki: "Ismeretlen port: " + port

            String message = switch (port) {
                case 22 -> "SSH - Figyelj rá!";
                case 80 -> "HTTP - Nem titkosított!";
                case 443 -> "HTTPS - Biztonságos";
                case 3389 -> "RDP - Távoli asztal nyitva!";
                default -> "Ismeretlen port: " + port;
            };

            System.out.println(message);


        }
    }
}
