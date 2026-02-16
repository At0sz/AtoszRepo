package gyakorlas.Hotel;

import java.util.HashMap;
import java.util.Scanner;

public class Recepcio {

    // Szobaszám (Integer) -> Szoba Objektum
    private HashMap<Integer, Szoba> szobak = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void nyitas() {
        // Szobák feltöltése
        szobak.put(101, new Szoba(101, 15000, 2));
        szobak.put(102, new Szoba(102, 25000, 3)); // Családi
        szobak.put(201, new Szoba(201, 40000, 2)); // Lakosztály (VIP)

        System.out.println("--- 🏨 GRAND HOTEL RECEPCIÓ 🏨 ---");

        while (true) {
            System.out.println("\n1. Szobák listázása");
            System.out.println("2. Szoba kiadása (Check-in)");
            System.out.println("3. Kijelentkezés (Check-out & Fizetés)");
            System.out.println("4. Bezárás");
            System.out.print("Válassz: ");

            if (!scanner.hasNextInt()) {
                scanner.next(); // Szemét ürítése
                continue;
            }
            int menu = scanner.nextInt();
            scanner.nextLine();

            if (menu == 4) break;

            if (menu == 1) {
                System.out.println("\n--- SZOBALISTA ---");
                // Tipp: A szobak.values() adja vissza az objektumokat
                for (Szoba sz : szobak.values()) {
                    System.out.println(sz);
                }
            } else if (menu == 2) {
                System.out.print("Melyik szobát kérik? (Szám): ");
                int szam = scanner.nextInt();
                scanner.nextLine();

                // --- 1. FELADAT: CHECK-IN ---
                // 1. Ellenőrizd: Létezik-e a szoba? (containsKey)
                // 2. Ha IGEN: Kérd le az objektumot (.get).
                // 3. Ellenőrizd: Szabad-e? (szoba.isSzabad())
                //    - Ha FOGLALT: Írd ki: "Sajnos ez már foglalt."
                //    - Ha SZABAD:
                //      - Kérd be a vendég nevét.
                //      - Hívd meg a szoba.lefoglal(nev) metódust.
                //      - Írd ki: "Sikeres bejelentkezés!"

                if (szobak.containsKey(szam)) {
                    Szoba szoba = szobak.get(szam);
                    if (szoba.isSzabad() == true) {
                        System.out.println("Mi a neve? ");
                        String neve = scanner.nextLine();
                        szoba.lefoglal(neve);
                        System.out.println("Sikeres foglalás: " + neve + " néven a : " + szoba + " szoba.");


                    } else {
                        System.out.println("Sajnos már foglalt a szoba!❌");
                    }


                } else {
                    System.out.println("Nincs ilyen szobaszám.");
                }

            } else if (menu == 3) {
                System.out.print("Melyik szobából jelentkeznek ki? ");
                int szam = scanner.nextInt();
                scanner.nextLine();

                // --- 2. FELADAT: CHECK-OUT ---
                // 1. Ellenőrizd: Létezik-e a szoba?
                // 2. Ha IGEN: Kérd le az objektumot.
                // 3. Ellenőrizd: Tényleg foglalt volt? (!szoba.isSzabad())
                //    - Ha SZABAD: "Ez a szoba üres volt, nem lehet kijelentkezni."
                //    - Ha FOGLALT:
                //      - Kérdezd meg: "Hány éjszakát maradt?" (int)
                //      - Számold ki a számlát: ejszakak * szoba.getArEjszakankent()
                //      - Írd ki a számlát.
                //      - Hívd meg a szoba.felszabadit() metódust! (Hogy újra kiadható legyen)

                if (szobak.containsKey(szam)) {
                    Szoba szoba = szobak.get(szam);
                    if (szoba.isSzabad() == true) {
                        System.out.println("Ez a szoba üres volt nem lehet kijelentkezni");
                    } else {
                        System.out.println("Hány éjszakát maradt? ");
                        int ejszaka = scanner.nextInt();
                        scanner.nextLine();
                        int szamla = ejszaka * szoba.getArEjszakankent();
                        System.out.println("A fizetendő összeg: " + szamla + "Ft. Ennyi éjszaka után: " + ejszaka);
                        System.out.println("Köszönjük ,hogy nálunk töltötte a pihenését! ❤");
                        szoba.felszabadit();
                    }


                } else {
                    System.out.println("Nincs ilyen szobaszám.");
                }
            }
        }
        System.out.println("Viszlát!");
    }
}