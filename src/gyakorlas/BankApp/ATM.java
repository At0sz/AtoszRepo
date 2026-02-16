package gyakorlas.BankApp;

import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    // Kártyaszám (String) -> Számla (Objektum)
    private HashMap<String, Bankszamla> kartyak = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void bekapcsolas() {
        // Teszt adatok
        kartyak.put("1234-5678", new Bankszamla("Kiss Pista", "1111", 50000));
        kartyak.put("9876-5432", new Bankszamla("Nagy Anna", "2222", 120000));

        System.out.println("--- 🏧 BANKAUTOMATA 🏧 ---");

        while (true) {
            System.out.print("\nKérem a kártyaszámot (vagy 'exit'): ");
            String kartyaSzam = scanner.nextLine();

            if (kartyaSzam.equals("exit")) break;

            // --- FELADAT 3: KERESD MEG A KÁRTYÁT! ---
            // 1. Ellenőrizd: kartyak.containsKey(kartyaSzam)?
            // 2. Ha NINCS: írd ki, hogy "Érvénytelen kártya".
            // 3. Ha VAN: Kérd le az objektumot: Bankszamla aktualis = kartyak.get(...);
            // 4. Kérd be a PIN kódot a felhasználótól.
            // 5. Ellenőrizd: aktualis.pinEllenorzes(beirPin).
            //    - Ha ROSSZ: Írd ki: "Hibás PIN!"
            //    - Ha JÓ: Hívd meg a menut: belsoMenu(aktualis);

            if (kartyak.containsKey(kartyaSzam)) {
                // ... Itt folytasd a logikát ...
                Bankszamla aktualis = kartyak.get(kartyaSzam);
                System.out.println("Kérem a PIN kódot: ");
                String pin = scanner.nextLine();
                if (aktualis.pinEllenorzes(pin)) {
                    System.out.println("Sikeres hitelesités");
                    belsoMenu(aktualis);
                } else {
                    System.out.println("Hibás pin");
                }


            } else {
                System.out.println("❌ Érvénytelen kártyaszám!");
            }
        }
    }

    // Ezt hívjuk meg, ha sikeres volt a belépés
    private void belsoMenu(Bankszamla szamla) {
        System.out.println("\nÜdvözöljük, " + szamla.getTulajdonos() + "!");

        while (true) {
            System.out.println("1. Egyenleg");
            System.out.println("2. Pénzfelvétel");
            System.out.println("3. Kilépés");
            System.out.print("Válasz: ");
            int valasztas = scanner.nextInt();
            scanner.nextLine(); // Enter törlése

            if (valasztas == 3) {
                System.out.println("Viszlát!");
                break; // Kilépünk a belső menüből, vissza a kártya kéréshez
            }

            if (valasztas == 1) {
                System.out.println("Egyenleg: " + szamla.getEgyenleg() + " Ft");
            } else if (valasztas == 2) {
                System.out.print("Mennyit szeretne felvenni? ");
                int osszeg = scanner.nextInt();
                scanner.nextLine();
                if (szamla.penzFelvet(osszeg)) {
                    System.out.println("Sikeres pénzfelvétel " + osszeg + " .Jelenlegi egyenleg: " + szamla.getEgyenleg());
                } else {
                    System.out.println("Nincs elég fedezet");
                }
                // --- FELADAT 4: PÉNZFELVÉTEL HÍVÁSA ---
                // Hívd meg a szamla.penzFelvet(osszeg) metódust!
                // Ha igazat ad vissza -> "Sikeres tranzakció, vegye el a pénzt."
                // Ha hamisat -> "Nincs fedezet!"


            }
        }
    }
}