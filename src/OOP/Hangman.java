package OOP;

import java.util.Scanner;

public class Hangman {

    // Igen, figyelünk a szabványra! :D
    public void jatekInditas() {

        String titkosSzo = "SZERETLEK";

        // Létrehozunk egy karaktertömböt, ami pont olyan hosszú, mint a szó
        char[] kitalaltBetuk = new char[titkosSzo.length()];

        // Feltöltjük aláhúzásjelekkel: [_, _, _, _]
        for (int i = 0; i < kitalaltBetuk.length; i++) {
            kitalaltBetuk[i] = '_';
        }

        int elet = 5;
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- AKASZTÓFA JÁTÉK ---");

        // FŐ CIKLUS: Addig megyünk, amíg van élet
        while (elet > 0) {

            // 1. Kiírjuk az aktuális állást (pl. _ A _ A)
            System.out.print("A szó: ");
            System.out.println(kitalaltBetuk); // A char tömböt a Java szépen kiírja egyben
            System.out.println("Élet: " + elet);

            // 2. Bekérjük a tippet
            System.out.print("Tippelj egy betűt: ");
            // Trükk: A Scanner nem tud csak char-t olvasni, ezért beolvassuk a Stringet,
            // és kivesszük a 0. (első) karakterét.
            char tipp = scanner.next().toUpperCase().charAt(0);

            // --- INNEN JÖSSZ TE! ---
            boolean voltTalalat = false;

            for (int i = 0; i < kitalaltBetuk.length; i++) {
                if (titkosSzo.charAt(i) == tipp) {
                    kitalaltBetuk[i] = tipp;
                    voltTalalat = true;
                }
            }
            if (!voltTalalat) {
                elet -= 1;
            }
            String aktualisAllapot = new String(kitalaltBetuk);

            // Megnézzük, hogy tartalmaz-e még alsóvonást
            if (!aktualisAllapot.contains("_")) {
                System.out.println("NYERTÉL! A szó: " + titkosSzo);
                break; // Kilépünk a while ciklusból
            }
            // Feladat:
            // 1. Hozz létre egy boolean változót (pl. voltTalalat = false)

            // 2. Menj végig a 'titkosSzo'-n egy for ciklussal (i = 0-tól a hosszáig)
            //    Segítség: titkosSzo.charAt(i) adja vissza az i-edik betűt.

            // 3. Ha a titkosSzo.charAt(i) megegyezik a tipp-pel:
            //    - Írd be a betűt a kitalaltBetuk[i] helyre!
            //    - Állítsd a voltTalalat-ot igazra!

            // 4. A ciklus után: Ha NEM volt találat (!voltTalalat), vonj le egy életet!

            // 5. Ellenőrizd, hogy nyert-e! (Ha a kitalaltBetuk már nem tartalmaz '_'-t)
            //    Ha nyert, írd ki és 'break;'

        } // While vége

        if (elet == 0) {
            System.out.println("Vesztettél! A szó ez volt: " + titkosSzo);
        }
    }
}

