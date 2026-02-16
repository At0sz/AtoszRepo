package gyakorlas.Autokolcsonzo;

import java.util.HashMap;
import java.util.Scanner;

public class Kolcsonzo {

    // KULCS: Rendszám (String) -> ÉRTÉK: Auto Objektum
    private HashMap<String, Auto> flotta = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void rendszerInditas() {
        // Feltöltés adatokkal
        flotta.put("ABC-123", new Auto("ABC-123", "Suzuki Swift", 5000));
        flotta.put("BMW-001", new Auto("BMW-001", "BMW X5", 25000));
        flotta.put("TES-999", new Auto("TES-999", "Tesla Model 3", 30000));

        System.out.println("--- 🚗 AUTÓKÖLCSÖNZŐ 🚗 ---");

        while (true) {
            System.out.println("\n1. Autók listázása");
            System.out.println("2. Kölcsönzés (Kiadás)");
            System.out.println("3. Visszavétel");
            System.out.println("4. Kilépés");
            System.out.print("Válassz: ");

            int menu = scanner.nextInt();
            scanner.nextLine();

            if (menu == 4) break;

            if (menu == 1) {
                System.out.println("\n--- JELENLEGI FLOTTA ---");
                // Végigmegyünk az értékeken (.values())
                for (Auto a : flotta.values()) {
                    System.out.println(a);
                }
            } else if (menu == 2) {
                System.out.print("Melyik rendszámot szeretnéd? ");
                String rendszam = scanner.nextLine();

                // --- 1. FELADAT: KÖLCSÖNZÉS ---
                // 1. Ellenőrizd: Létezik-e ilyen rendszám a map-ben? (containsKey)
                // 2. Ha IGEN: Kérd le az autót (.get).
                // 3. Próbáld meg kivenni: boolean siker = auto.kivesz();
                // 4. Ha SIKERES (siker == true):
                //    - Kérdezd meg hány napra viszi? (int napok)
                //    - Számold ki az árat: napok * auto.getNapiAr()
                //    - Írd ki: "Sikeres kölcsönzés! Fizetendő: ... Ft"
                // 5. Ha NEM SIKERES (mert már ki van adva):
                //    - Írd ki: "Ez az autó jelenleg nem elérhető."

                if (flotta.containsKey(rendszam)) {
                    // IDE ÍRD A KÓDOT...
                    Auto kocsi = flotta.get(rendszam);
                    boolean siker = kocsi.kivesz();
                    if (siker) {
                        System.out.println("Mennyi napra szeretné bérelni?");
                        int napok = scanner.nextInt();
                        scanner.nextLine();
                        int osszeg = kocsi.getNapiAr() * napok;
                        System.out.println("Sikeres kölcsönzés! Fizetendő: " + osszeg + " Ft. Ennyi napra: " + napok);
                    } else {
                        System.out.println("Ez az autó jelenleg nem elérhető.");
                    }


                } else {
                    System.out.println("Nincs ilyen rendszámú autó.");
                }

            } else if (menu == 3) {
                System.out.print("Melyik autót hozták vissza? (Rendszám): ");
                String rendszam = scanner.nextLine();

                // --- 2. FELADAT: VISSZAVÉTEL ---
                // 1. Ellenőrizd, hogy létezik-e.
                // 2. Ha igen, kérd le az autót.
                // 3. Ellenőrizd: Ha "Szabad" (auto.isElerheto() == true), akkor Hiba: "Ez nincs is kiadva!"
                // 4. Különben (ha ki van adva):
                //    - Hívd meg az auto.visszahoz() metódust.
                //    - Írd ki: "Autó visszavéve. Köszönjük!"
                if (flotta.containsKey(rendszam)) {
                    Auto kocsi = flotta.get(rendszam);
                    if (!kocsi.isElerheto()) {
                        kocsi.visszahoz();
                        System.out.println("Autó visszavéve");
                    }
                }


            }
        }
    }
}