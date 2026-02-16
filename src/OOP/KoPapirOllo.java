package OOP; // Ha nem OOP mappában van, ezt töröld!

import java.util.Random;
import java.util.Scanner;

public class KoPapirOllo {

    public void jatekInditas() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("\n--- ✂️ KŐ-PAPÍR-OLLÓ ✂️ ---");
        System.out.println("Válassz: 1=Kő, 2=Papír, 3=Olló");

        int jatekosTipp = scanner.nextInt();

        // A gép sorsol 1 és 3 között
        int gepTipp = random.nextInt(3) + 1;

        // Kiírjuk, mit választott a gép
        if (gepTipp == 1) System.out.println("Gép: Kő");
        else if (gepTipp == 2) System.out.println("Gép: Papír");
        else System.out.println("Gép: Olló");

        // Eredmény eldöntése
        if (jatekosTipp == gepTipp) {
            System.out.println("Döntetlen!");
        } else if ((jatekosTipp == 1 && gepTipp == 3) ||
                (jatekosTipp == 2 && gepTipp == 1) ||
                (jatekosTipp == 3 && gepTipp == 2)) {
            System.out.println("🎉 Nyertél!");
        } else {
            System.out.println("💀 Vesztettél!");
        }

        System.out.println("--- Játék vége ---\n");
    }
}
