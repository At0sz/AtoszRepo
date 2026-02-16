package gyakorlas.OkosOtthon;

import java.util.ArrayList;
import java.util.Scanner;

public class Vezerlo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // A lista típusa az INTERFÉSZ! Így bármit bele tudunk tenni, ami kapcsolható.
        ArrayList<Kapcsolhato> eszkozok = new ArrayList<>();

        // --- FELADAT 1: Töltsd fel a listát! ---
        eszkozok.add(new Lampa());
        eszkozok.add(new Tv());
        eszkozok.add(new Klima());
        // eszkozok.add(new Tv()); // Ezt neked kell megírni!
        // eszkozok.add(new Klima());

        System.out.println("--- 🏠 OKOSOTTHON VEZÉRLŐ 🏠 ---");

        while (true) {
            System.out.println("\n1. Minden felkapcsolása (Party Mód)");
            System.out.println("2. Minden lekapcsolása (Távozás)");
            System.out.println("3. Kilépés");
            System.out.print("Válassz: ");

            int menu = scanner.nextInt();

            if (menu == 3) break;

            if (menu == 1) {
                System.out.println("\n--- PARTY MÓD ---");
                // --- FELADAT 2: Menj végig a listán és kapcsolj be mindent! ---
                for (Kapcsolhato eszkoz : eszkozok) {
                    eszkoz.beKapcsol();
                }
            } else if (menu == 2) {
                System.out.println("\n--- MINDEN LEKAPCSOLÁSA ---");
                // --- FELADAT 3: Menj végig és kapcsolj le mindent! ---
                for (Kapcsolhato eszkoz : eszkozok) {
                    eszkoz.kiKapcsol();
                }


            }
        }
    }
}