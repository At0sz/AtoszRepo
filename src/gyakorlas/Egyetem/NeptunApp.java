package gyakorlas.Egyetem;

import java.util.HashMap;
import java.util.Scanner;

public class NeptunApp {

    // KULCS: Kurzus kódja (pl. "PROG1") -> ÉRTÉK: Kurzus Objektum
    private HashMap<String, Kurzus> kurzusok = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void inditas() {
        // Alap kurzusok létrehozása
        kurzusok.put("PROG1", new Kurzus("Programozás Alapjai", 3)); // Csak 3 hely van!
        kurzusok.put("MAT1", new Kurzus("Diszkrét Matek", 30));
        kurzusok.put("ANG1", new Kurzus("Szakmai Angol", 15));

        System.out.println("--- 🎓 NEPTUN LITE RENDSZER 🎓 ---");

        while (true) {
            System.out.println("\n1. Kurzusok listázása");
            System.out.println("2. Tárgyfelvétel");
            System.out.println("3. Tárgy leadása");
            System.out.println("4. Kurzus névsor megtekintése");
            System.out.println("5. Kilépés");
            System.out.print("Válassz: ");

            int menu = scanner.nextInt();
            scanner.nextLine();

            if (menu == 5) break;

            if (menu == 1) {
                System.out.println("\n--- ELÉRHETŐ KURZUSOK ---");
                for (String kod : kurzusok.keySet()) {
                    System.out.println(kod + ": " + kurzusok.get(kod));
                }
            } else if (menu == 2) {
                System.out.print("Melyik tárgyat? (Kód, pl. PROG1): ");
                String kod = scanner.nextLine().toUpperCase();

                // --- FELADAT 3: TÁRGYFELVÉTEL LOGIKA ---
                // 1. Ellenőrizd: Létezik-e a kurzus a Map-ben?
                // 2. Ha IGEN:
                //    - Kérd be a diák nevét és neptun kódját.
                //    - Hozz létre egy új Diak objektumot.
                //    - Hívd meg a kurzus.diakFelvetele(diak) metódust.
                //    - Ha igazat ad vissza -> "Sikeres felvétel!"
                //    - Ha hamisat -> "Sikertelen (Betelt vagy már felvette)."
                if (kurzusok.containsKey(kod)) {
                    System.out.println("Résztvevő neve: ");
                    String nev = scanner.nextLine();
                    System.out.println("Résztvevő neptun kódja : ");
                    String neptunKod = scanner.nextLine();

                    Diak friss = new Diak(nev, neptunKod);
                    Kurzus kivalasztott = kurzusok.get(kod);

                    boolean siker = kivalasztott.diakFelvetele(friss);

                    if (siker) {
                        System.out.println("Sikertelen felvétel(betelt vagy már felvette)");

                    } else {
                        System.out.println("Sikeres felvétel: " + friss + " ,erre a kurzusra: " + kivalasztott);
                    }

                } else {
                    System.out.println("Nem létező tárgy!");
                }


            } else if (menu == 3) {
                System.out.print("Melyik tárgyról? (Kód): ");
                String kod = scanner.nextLine().toUpperCase();
                if (kurzusok.containsKey(kod)) {
                    Kurzus aktualisKurzus = kurzusok.get(kod);
                    System.out.println("Kérem a neptun kódot: ");
                    String neptunKod = scanner.nextLine();
                    boolean siker = aktualisKurzus.diakLeadasa(neptunKod);
                    if (siker) {
                        System.out.println("✅ Tárgy sikeresen leadva.");
                    } else {
                        System.out.println("❌ Hiba: Nincs ilyen diák ezen a kurzuson.");
                    }


                }

                // --- FELADAT 4: TÁRGY LEADÁS LOGIKA ---
                // 1. Ellenőrizd a kurzust.
                // 2. Ha létezik, kérd be a diák NEPTUN kódját.
                // 3. Hívd meg a kurzus.diakLeadasa(neptunKod) metódust.
                // 4. Írd ki az eredményt.
            } else if (menu == 4) {
                System.out.print("Melyik tárgy listája? (Kód): ");
                String kod = scanner.nextLine().toUpperCase();

                if (kurzusok.containsKey(kod)) {
                    kurzusok.get(kod).listazResztvevok();
                } else {
                    System.out.println("Nincs ilyen tárgy.");
                }
            }
        }
        System.out.println("Rendszer leállítva.");
    }
}