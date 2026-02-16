package gyakorlas.mozi;

import java.util.Scanner;

public class Mozi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Létrehozunk egy 5 soros, 8 székes termet
        Terem nagyTerem = new Terem(5, 8);

        // 2. A műsoron lévő film
        Film aktualisFilm = new Film("Avatar 3", "Sci-Fi", 2500);

        System.out.println("--- 🍿 JAVA MOZI JEGYFOGLALÓ 🍿 ---");
        System.out.println("Műsoron: " + aktualisFilm);

        while (true) {
            System.out.println("\n1. Terem megtekintése");
            System.out.println("2. Jegyfoglalás");
            System.out.println("3. Statisztika (Bevétel)");
            System.out.println("4. Jegy visszaváltása [ÚJ]");
            System.out.println("5. Kilépés");
            System.out.print("Válassz: ");

            int menu = scanner.nextInt();

            if (menu == 5) break;

            if (menu == 1) {
                nagyTerem.teremRajzolas();
            } else if (menu == 2) {
                nagyTerem.teremRajzolas();
                System.out.print("Melyik sor? ");
                int sor = scanner.nextInt();
                System.out.print("Melyik szék? ");
                int szek = scanner.nextInt();

                boolean siker = nagyTerem.foglal(sor, szek);

                if (siker) {
                    System.out.println("✅ Sikeres foglalás! Fizetendő: " + aktualisFilm.getAr() + " Ft");
                }
            } else if (menu == 3) {
                int osszesHely = 5 * 8;
                int szabad = nagyTerem.szabadHelyekSzama();
                int foglalt = osszesHely - szabad;
                int bevetel = foglalt * aktualisFilm.getAr();

                System.out.println("--- STATISZTIKA ---");
                System.out.println("Szabad helyek: " + szabad);
                System.out.println("Eladott jegyek: " + foglalt);
                System.out.println("Mai bevétel: " + bevetel + " Ft");
            } else if (menu == 4) {
                // --- 4. FELADAT: LEMONDÁS ---
                System.out.println("--- JEGY VISSZAVÁLTÁS ---");
                System.out.print("Melyik sor? ");
                int sor = scanner.nextInt();
                System.out.print("Melyik szék? ");
                int szek = scanner.nextInt();

                // Ezt a metódust kell megírnod a Terem.java-ban!
                boolean siker = nagyTerem.lemondas(sor, szek);

                if (siker) {
                    System.out.println("✅ A jegyet sikeresen visszaváltottuk.");
                }
            }
        }
        System.out.println("Viszlát!");
    }
}