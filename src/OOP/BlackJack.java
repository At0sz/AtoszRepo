package OOP; // Ha kell...

import java.util.Random;
import java.util.Scanner;

public class BlackJack {

    public void jatekInditas() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("\n--- ♣️ BLACKJACK (21) ♣️ ---");

        // 1. A JÁTÉKOS KÖRE
        int jatekosPont = 0;
        // Kezdésnek adunk két lapot
        jatekosPont += random.nextInt(10) + 2; // 2-11 közötti lap
        jatekosPont += random.nextInt(10) + 2;

        System.out.println("Kezdő lapjaid összege: " + jatekosPont);

        boolean jatekosKör = true;
        while (jatekosKör) {
            if (jatekosPont > 21) {
                System.out.println("💀 BSOKALLTÁL! (Több mint 21)");
                System.out.println("Vesztettél.");
                return; // Azonnal vége
            }
            if (jatekosPont == 21) {
                System.out.println("BLACKJACK! 21-ed van!");
                break; // Kilépünk a ciklusból, jöhet a gép
            }

            System.out.print("Mit lépsz? (1 = Húzok, 2 = Megállok): ");
            int valasztas = scanner.nextInt();

            if (valasztas == 1) {
                int ujLap = random.nextInt(10) + 2;
                jatekosPont += ujLap;
                System.out.println("Húztál egy: " + ujLap + "-est. Jelenlegi pontod: " + jatekosPont);
            } else {
                System.out.println("Megálltál " + jatekosPont + " ponton.");
                jatekosKör = false;
            }
        }

        // 2. A DEALER (GÉP) KÖRE
        System.out.println("\n--- A DEALER KÖRE ---");
        int dealerPont = random.nextInt(10) + 2; // Egy lappal kezd
        System.out.println("Dealer kezdő lapja: " + dealerPont);

        // A Dealer szabálya: 17 alatt kötelező húznia
        while (dealerPont < 17) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            } // Izgalom...
            int ujLap = random.nextInt(10) + 2;
            dealerPont += ujLap;
            System.out.println("Dealer húzott: " + ujLap + " (Összesen: " + dealerPont + ")");
        }

        // 3. EREDMÉNYHIRDETÉS
        System.out.println("\n--- VÉGEREDMÉNY ---");
        System.out.println("Te: " + jatekosPont + " | Dealer: " + dealerPont);

        if (dealerPont > 21) {
            System.out.println("🎉 A Dealer besokallt! NYERTÉL!");
        } else if (dealerPont > jatekosPont) {
            System.out.println("💀 A Dealernek több van. VESZTETTÉL.");
        } else if (dealerPont < jatekosPont) {
            System.out.println("🎉 Neked van több! NYERTÉL!");
        } else {
            System.out.println("🤝 Döntetlen (Push)!");
        }
        System.out.println("--------------------------\n");
    }
}
