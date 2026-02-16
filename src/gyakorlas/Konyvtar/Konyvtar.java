package gyakorlas.Konyvtar;

import java.util.HashMap;
import java.util.Scanner;

public class Konyvtar {

    // A Map: ISBN (String) -> Konyv (Objektum)
    private HashMap<String, Konyv> polcok = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void inditas() {
        // Pár teszt adat
        polcok.put("978-1", new Konyv("Harry Potter", "J.K. Rowling"));
        polcok.put("978-2", new Konyv("Gyűrűk Ura", "J.R.R. Tolkien"));

        System.out.println("--- 🏛️ KÖNYVTÁR RENDSZER 🏛️ ---");

        while (true) {
            System.out.println("\n1. Új könyv felvétele");
            System.out.println("2. Keresés ISBN alapján");
            System.out.println("3. Könyv törlése");
            System.out.println("4. Összes könyv listázása");
            System.out.println("5. Kilépés");
            System.out.print("Válassz: ");

            int menu = scanner.nextInt();
            scanner.nextLine();

            if (menu == 5) break;

            if (menu == 1) {
                System.out.print("ISBN szám: ");
                String isbn = scanner.nextLine();

                // --- 1. FELADAT: FELVÉTEL ---
                // Ellenőrizd: Ha már létezik (containsKey), írd ki: "Már van ilyen!"
                // Ha nem: Kérd be a Címet és Szerzőt, hozz létre egy új Konyv-et,
                // és tedd be a Map-be a .put() metódussal!

                if (polcok.containsKey(isbn)) {
                    System.out.println("Hiba: Ez az ISBN már létezik!");
                } else {
                    System.out.print("Cím: ");
                    String cim = scanner.nextLine();
                    System.out.print("Szerző: ");
                    String szerzo = scanner.nextLine();

                    // IDE ÍRD A PUT PARANCSOT:
                    polcok.put(isbn, new Konyv(cim, szerzo));


                    System.out.println("Könyv sikeresen felvéve.");
                }

            } else if (menu == 2) {
                System.out.print("Keresett ISBN: ");
                String isbn = scanner.nextLine();

                // --- 2. FELADAT: KERESÉS ---
                // Ha létezik (containsKey):
                // Kérd le az objektumot (.get), és írd ki a képernyőre!
                // Ha nem: Írd ki, hogy nincs találat.
                if (polcok.containsKey(isbn)) {
                    System.out.println(polcok.get(isbn).toString());
                } else {
                    System.out.println("Nincs ilyen találat!");
                }


            } else if (menu == 3) {
                System.out.print("Törlendő ISBN: ");
                String isbn = scanner.nextLine();

                // --- 3. FELADAT: TÖRLÉS ---
                // Ha létezik: Töröld ki a .remove(kulcs) metódussal!
                // Írd ki, hogy sikeres volt.
                if (polcok.containsKey(isbn)) {
                    polcok.remove(isbn);
                    System.out.println("Sikeres törlés");
                } else {
                    System.out.println("Nem található ilyen ISBN szám");
                }


            } else if (menu == 4) {
                System.out.println("--- KÖNYVLISTA ---");
                // --- 4. FELADAT: LISTÁZÁS ---
                // Használd a polcok.entrySet() vagy polcok.values() ciklust!
                // Írd ki az összes könyvet. (Ha a .entrySet()-et használod, kiírhatod az ISBN-t is elé).

                for (String kulcs : polcok.keySet()) {
                    // Lekérjük a kulcshoz tartozó értéket (Könyvet)
                    Konyv konyv = polcok.get(kulcs);

                    // Kiírjuk szépen formázva: ISBN -> Könyv adatai
                    System.out.println("ISBN: " + kulcs + " -> " + konyv);
                }


            }
        }
    }
}
