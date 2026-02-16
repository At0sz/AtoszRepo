package gyakorlas.Parkolo;

import java.util.HashMap;
import java.util.Scanner;

public class ParkoloHaz {

    private HashMap<String, Jegy> aktivParkolasok = new HashMap<>();

    private final int NORMAL_AR = 400;
    private final int VIP_AR = 800;

    private Scanner scanner = new Scanner(System.in);

    public void rendszerInditas() {
        System.out.println("--- 🅿️ PARKOLÓHÁZ RENDSZER 🅿️ ---");

        while (true) {
            System.out.println("\n1. Behajtás (Jegy kiadása)");
            System.out.println("2. Kihajtás (Fizetés)");
            System.out.println("3. Jelenleg bent lévő autók");
            System.out.println("4. Kilépés");
            System.out.print("Válassz: ");

            // Biztonsági ellenőrzés szám beolvasásához
            if (!scanner.hasNextInt()) {
                scanner.next(); // "Szemét" takarítása
                continue;
            }
            int menu = scanner.nextInt();
            scanner.nextLine();

            if (menu == 4) break;

            if (menu == 1) {
                System.out.print("Rendszám: ");
                String rendszam = scanner.nextLine();

                if (aktivParkolasok.containsKey(rendszam)) {
                    System.out.println("Hiba: Ez az autó már a parkolóban van!");
                } else {
                    System.out.print("Hány órakor érkezett? (0-23): ");
                    int erkezett = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("VIP helyre áll? (igen/nem): ");
                    String vipKerdes = scanner.nextLine();

                    // Egyszerűsített logika:
                    boolean vipHelyre = vipKerdes.equalsIgnoreCase("igen");

                    Jegy jegy = new Jegy(rendszam, erkezett, vipHelyre);
                    aktivParkolasok.put(rendszam, jegy);
                    System.out.println("✅ Sorompó felnyitva. Jegy kiadva.");
                }

            } else if (menu == 2) {
                System.out.print("Kihajtó autó rendszáma: ");
                String rendszam = scanner.nextLine();

                if (aktivParkolasok.containsKey(rendszam)) {
                    Jegy jegy = aktivParkolasok.get(rendszam);

                    System.out.print("Távozás órája? ");
                    int tavozas = scanner.nextInt();
                    scanner.nextLine();

                    // Időtartam számítása
                    int idotartam = tavozas - jegy.getErkezesiOra();
                    if (idotartam <= 0) {
                        idotartam = 1; // Minimum 1 órát fizetni kell
                    }

                    // Ár számítása (Ternary operátorral elegánsabb: ? :)
                    // Ha VIP -> VIP_AR, ha nem -> NORMAL_AR
                    int tarifa = jegy.isVip() ? VIP_AR : NORMAL_AR;

                    int fizetendo = idotartam * tarifa;

                    System.out.println("Időtartam: " + idotartam + " óra");
                    System.out.println("Fizetendő összeg: " + fizetendo + " Ft");

                    // FONTOS: Törölni kell a map-ből, mert kiment!
                    aktivParkolasok.remove(rendszam);
                    System.out.println("👋 Viszontlátásra!");

                } else {
                    System.out.println("❌ Nem található ilyen rendszám a bent lévők között.");
                }

            } else if (menu == 3) {
                System.out.println("\n--- BENT LÉVŐ AUTÓK ---");
                if (aktivParkolasok.isEmpty()) {
                    System.out.println("A parkolóház üres.");
                } else {
                    for (Jegy j : aktivParkolasok.values()) {
                        System.out.println(j);
                    }
                }
            }
        }
    }
}