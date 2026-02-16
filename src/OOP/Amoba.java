package OOP; // Ha kell...

import java.util.Scanner;

public class Amoba {

    public void jatekInditas() {
        Scanner scanner = new Scanner(System.in);

        // Ez a pálya: 9 karakter, kezdetben a számokkal
        char[] tabla = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

        char aktualisJatekos = 'X'; // Az 'X' kezd
        boolean vanNyertes = false;
        int lepesekSzama = 0; // Ha eléri a 9-et és nincs nyertes -> Döntetlen

        System.out.println("\n--- ❌ AMŐBA (TIC-TAC-TOE) ⭕ ---");
        System.out.println("Két játékos mód! Írd be a számot, hova raksz.");

        while (true) {
            // 1. Kirajzoljuk a pályát (ez a lenti metódust hívja)
            tablaRajzolas(tabla);

            // 2. Bekérjük a lépést
            System.out.print("Játékos '" + aktualisJatekos + "', hova lépsz? (1-9): ");
            int valasztas = scanner.nextInt();

            // Index korrekció: A játékos 1-et mond, de az a tömb 0. eleme!
            int index = valasztas - 1;

            // Ellenőrzés: Érvényes a szám? (0 és 8 között van?)
            if (index < 0 || index > 8) {
                System.out.println("Hibás szám! 1 és 9 között válassz.");
                continue;
            }

            // Ellenőrzés: Foglalt a hely? (Nem egyezik meg az eredeti számkarakterrel?)
            // Trükk: Ha a táblán már 'X' vagy 'O' van, akkor nem rakhatunk oda.
            if (tabla[index] == 'X' || tabla[index] == 'O') {
                System.out.println("Ez a hely már foglalt!");
                continue;
            }

            tabla[index] = aktualisJatekos;

            lepesekSzama++;

            if (
                // --- SOROK ---
                    (tabla[0] == tabla[1] && tabla[1] == tabla[2]) || // 1. sor
                            (tabla[3] == tabla[4] && tabla[4] == tabla[5]) || // 2. sor
                            (tabla[6] == tabla[7] && tabla[7] == tabla[8]) || // 3. sor

                            // --- OSZLOPOK ---
                            (tabla[0] == tabla[3] && tabla[3] == tabla[6]) || // 1. oszlop
                            (tabla[1] == tabla[4] && tabla[4] == tabla[7]) || // 2. oszlop
                            (tabla[2] == tabla[5] && tabla[5] == tabla[8]) || // 3. oszlop

                            // --- ÁTLÓK ---
                            (tabla[0] == tabla[4] && tabla[4] == tabla[8]) || // Bal-fentről jobb-le
                            (tabla[2] == tabla[4] && tabla[4] == tabla[6])    // Jobb-fentről bal-le


            ) {
                tablaRajzolas(tabla); // Kirajzoljuk a végső állást
                System.out.println("🎉 GRATULÁLOK! A '" + aktualisJatekos + "' játékos győzött!");
                break; // Vége a játéknak
            }

            if (lepesekSzama == 9) {
                System.out.println("Döntetlen");
                break;
            }
            if (aktualisJatekos == 'X') {
                aktualisJatekos = 'O';
            } else {
                aktualisJatekos = 'X';
            }

        }
    }


    public void tablaRajzolas(char[] t) {
        System.out.println("\n " + t[0] + " | " + t[1] + " | " + t[2] + " ");
        System.out.println("---|---|---");
        System.out.println(" " + t[3] + " | " + t[4] + " | " + t[5] + " ");
        System.out.println("---|---|---");
        System.out.println(" " + t[6] + " | " + t[7] + " | " + t[8] + " \n");
    }
}
