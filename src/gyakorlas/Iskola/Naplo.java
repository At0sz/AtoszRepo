package gyakorlas.Iskola;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Naplo {

    // Itt a varázslat: A Map értéke egy Lista!
    private HashMap<String, ArrayList<Integer>> diakok = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void naploInditas() {
        System.out.println("\n--- 📚 DIGITÁLIS NAPLÓ RENDSZER 📚 ---");

        while (true) {
            System.out.println("\n1. Új diák felvétele");
            System.out.println("2. Jegy beírása");
            System.out.println("3. Diák átlaga");
            System.out.println("4. Osztályátlag");
            System.out.println("5. Kilépés");
            System.out.print("Válassz: ");

            int menu = scanner.nextInt();
            scanner.nextLine(); // Enter kezelése

            if (menu == 5) break;

            if (menu == 1) {
                System.out.print("Név: ");
                String nev = scanner.nextLine();

                // --- 1. FELADAT: DIÁK LÉTREHOZÁSA ---
                // Ellenőrizd: containsKey(nev)? Ha igen -> Hiba.
                // Ha nem: diakok.put(nev, new ArrayList<>());
                // (Fontos: egy üres listát kell betenni neki kezdésnek!)

                if (diakok.containsKey(nev)) {
                    System.out.println("Már létezik!");
                } else {
                    diakok.put(nev, new ArrayList<>());
                    System.out.println("Diák felvéve.");
                }

            } else if (menu == 2) {
                System.out.print("Kinek: ");
                String nev = scanner.nextLine();
                if (diakok.containsKey(nev)) {
                    System.out.println("Milyen jegy?");
                    int jegy = scanner.nextInt();
                    scanner.nextLine();
                    ArrayList<Integer> jegyek = diakok.get(nev);
                    jegyek.add(jegy);
                }
                // --- 2. FELADAT: JEGY BEÍRÁSA ---
                // 1. Ellenőrizd, hogy létezik-e a diák.
                // 2. Ha igen, kérd be a jegyet (int).
                // 3. Kérd le a listáját: ArrayList<Integer> jegyek = diakok.get(nev);
                // 4. Add hozzá az új jegyet a listához: jegyek.add(jegy);


            } else if (menu == 3) {
                System.out.print("Kinek: ");
                String nev = scanner.nextLine();

                if (diakok.containsKey(nev)) {
                    ArrayList<Integer> jegyek = diakok.get(nev);

                    if (jegyek.isEmpty()) {
                        System.out.println("Nincs még jegye!");
                    } else {
                        // --- ITT AZ ÁTLAGSZÁMÍTÁS LOGIKÁJA ---
                        int osszes = 0;

                        // Végigmegyünk a jegyeken
                        for (Integer jegy : jegyek) {
                            osszes += jegy; // Hozzáadjuk a kasszához
                        }

                        // Kiszámoljuk az átlagot (double-re kasztolva a pontosságért!)
                        double atlag = (double) osszes / jegyek.size();

                        System.out.println(nev + " átlaga: " + String.format("%.2f", atlag));
                    }
                } else {
                    System.out.println("Nincs ilyen diák.");
                }
                // --- 3. FELADAT: ÁTLAG SZÁMÍTÁS ---
                // 1. Ha létezik, kérd le a listáját.
                // 2. Ha a lista üres (.isEmpty()), írd ki, hogy nincs jegye.
                // 3. Ha vannak jegyei, menj végig rajtuk (for-each), add össze, és osszad el a darabszámmal (.size()).
                //    Vigyázz: Az osztásnál double-t használj!


            } else if (menu == 4) {

                // --- 4. FELADAT MEGOLDÁSA (OSZTÁLYÁTLAG) ---
                System.out.println("\n--- 📊 OSZTÁLY STATISZTIKA 📊 ---");

                double osztalyOsszesAtlag = 0;
                int aktivDiakokSzama = 0; // Csak az számít, akinek van jegye!

                // Végigmegyünk minden diákon (keySet)
                for (String nev : diakok.keySet()) {
                    ArrayList<Integer> jegyek = diakok.get(nev);

                    // Csak akkor számolunk, ha van jegye
                    if (!jegyek.isEmpty()) {
                        // 1. Kiszámoljuk az ő átlagát
                        int osszeg = 0;
                        for (int j : jegyek) osszeg += j;
                        double diakAtlag = (double) osszeg / jegyek.size();

                        // 2. Kiírjuk szépen listázva
                        System.out.println("- " + nev + ": " + String.format("%.2f", diakAtlag));

                        // 3. Hozzáadjuk a "nagy közösbe"
                        osztalyOsszesAtlag += diakAtlag;
                        aktivDiakokSzama++;
                    }
                }

                System.out.println("-------------------------");

                if (aktivDiakokSzama > 0) {
                    // Az összes átlag átlaga
                    double vegsoAtlag = osztalyOsszesAtlag / aktivDiakokSzama;
                    System.out.println("🏆 OSZTÁLYÁTLAG: " + String.format("%.2f", vegsoAtlag));
                } else {
                    System.out.println("Még senkinek nincs jegye.");
                    // Ez egy profi feladat, ha van kedved hozzá:
                    // Menj végig az összes diákon (.keySet()), és számold ki mindenkinek az átlagát, majd azoknak az átlagát.
                    // VAGY: Egyszerűen listázd ki mindenki átlagát egymás alá.
                }
            }
        }
    }
}