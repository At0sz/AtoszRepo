package gyakorlas.Raktar;

import java.util.HashMap;
import java.util.Scanner;

public class RaktarProgram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // A Raktár: Név (Kulcs) -> Termék Objektum (Érték)
        HashMap<String, Termek> raktar = new HashMap<>();

        // Kezdő áruk
        raktar.put("Tej", new Termek("Tej", 300, 10));
        raktar.put("Kenyér", new Termek("Kenyér", 450, 5));
        raktar.put("Csoki", new Termek("Csoki", 250, 20));

        System.out.println("--- 📦 RAKTÁRKEZELŐ RENDSZER 📦 ---");

        while (true) {
            System.out.println("\n1. Leltár (Lista)");
            System.out.println("2. Új termék felvétele");
            System.out.println("3. Eladás (Vevő vásárol)");
            System.out.println("4. Készletfeltöltés (Áruérkezés)");
            System.out.println("5. Kilépés");
            System.out.print("Válassz: ");

            int menu = scanner.nextInt();
            scanner.nextLine();

            if (menu == 5) break;

            if (menu == 1) {
                System.out.println("\n--- JELENLEGI KÉSZLET ---");
                for (Termek t : raktar.values()) {
                    System.out.println(t);
                }
            } else if (menu == 2) {
                System.out.print("Termék neve: ");
                String nev = scanner.nextLine();

                if (raktar.containsKey(nev)) {
                    System.out.println("Hiba: Ez a termék már létezik! Használd a feltöltést.");
                } else {
                    System.out.print("Ár: ");
                    int ar = scanner.nextInt();
                    System.out.print("Kezdő készlet (db): ");
                    int db = scanner.nextInt();
                    scanner.nextLine();

                    Termek ujTermek = new Termek(nev, ar, db);
                    raktar.put(nev, ujTermek);
                    System.out.println("Termék felvéve.");
                }
            } else if (menu == 3) {
                System.out.print("Mit szeretne venni? ");
                String nev = scanner.nextLine();

                if (raktar.containsKey(nev)) {
                    System.out.print("Hány darabot? ");
                    int db = scanner.nextInt();
                    scanner.nextLine();

                    Termek t = raktar.get(nev);

                    boolean siker = t.eladas(db);
                    if (siker) {
                        int fizetendo = db * t.getAr();
                        System.out.println("✅ Sikeres vásárlás! Fizetendő: " + fizetendo + " Ft");
                        System.out.println("Maradék készlet: " + t.getKeszlet() + " db");
                    } else {
                        System.out.println("❌ Nincs ennyi a raktáron! Jelenleg csak " + t.getKeszlet() + " db van.");
                    }
                } else {
                    System.out.println("Nincs ilyen termékünk.");
                }
            } else if (menu == 4) {
                System.out.print("Melyik termék érkezett? ");
                String nev = scanner.nextLine();

                if (raktar.containsKey(nev)) {
                    // Lekérjük a terméket
                    Termek t = raktar.get(nev);

                    System.out.print("Mennyi érkezett? ");
                    int darab = scanner.nextInt();
                    scanner.nextLine();

                    t.feltoltes(darab);

                    System.out.println("✅ Készlet feltöltve. Új darabszám: " + t.getKeszlet());

                } else {
                    System.out.println("Ilyen termék még nincs a rendszerben. Vedd fel újként (2-es gomb)!");
                }
            }
        }
        System.out.println("Viszlát!");
    }
}