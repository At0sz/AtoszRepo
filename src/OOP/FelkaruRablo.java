package OOP;

import java.util.Random;
import java.util.Scanner;

public class FelkaruRablo {

    public void jatekInditas() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // A lehetséges szimbólumok
        String[] ikonok = {"🍒", "🍋", "🔔", "💎", "7️⃣"};
        int egyenleg = 1000; // Kezdő pénz

        System.out.println("\n--- 🎰 FÉLKARÚ RABLÓ 🎰 ---");
        System.out.println("Kezdő egyenleged: " + egyenleg + " Ft");

        while (egyenleg > 0) {
            System.out.println("\nMennyit teszel fel? (0 = Kilépés)");
            System.out.print("Tét: ");
            int tet = scanner.nextInt();

            if (tet == 0) {
                break; // Kilépés
            }

            if (tet > egyenleg) {
                System.out.println("Nincs ennyi pénzed! (Egyenleg: " + egyenleg + ")");
                continue; // Újra kérjük a tétet
            }

            if (tet <= 0) {
                System.out.println("Érvénytelen tét.");
                continue;
            }

            // Levonjuk a tétet
            egyenleg -= tet;

            System.out.println("Pörgetés... 🎲");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            } // Kis szünet az izgalomért

            // Sorsolunk 3 szimbólumot (0-tól 4-ig az indexek)
            int a = random.nextInt(ikonok.length);
            int b = random.nextInt(ikonok.length);
            int c = random.nextInt(ikonok.length);

            // Kiírjuk a pörgetést
            System.out.println("-------------------");
            System.out.println("| " + ikonok[a] + " | " + ikonok[b] + " | " + ikonok[c] + " |");
            System.out.println("-------------------");

            // Nyerés ellenőrzése
            if (a == b && b == c) {
                // JACKPOT (Minden egyforma)
                int nyeremeny = tet * 10;
                egyenleg += nyeremeny;
                System.out.println("🎉 JACKPOT! Három egyforma! Nyeremény: " + nyeremeny + " Ft");
            } else if (a == b || b == c || a == c) {
                // KIS NYEREMÉNY (Kettő egyforma)
                int nyeremeny = tet * 2;
                egyenleg += nyeremeny;
                System.out.println("✨ Két egyforma! Nyeremény: " + nyeremeny + " Ft");
            } else {
                System.out.println("💀 Sajnos nem nyertél.");
            }

            System.out.println("Jelenlegi egyenleged: " + egyenleg + " Ft");
        }

        System.out.println("\nJáték vége! Végső egyenleg: " + egyenleg + " Ft");
        if (egyenleg == 0) System.out.println("Sajnos csődbe mentél... 💸");
    }
}