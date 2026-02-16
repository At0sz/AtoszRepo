package gyakorlas.Automata;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ItalAutomata automata = new ItalAutomata();

        System.out.println("--- 🤖 JAVA ITALAUTOMATA 🤖 ---");

        while (true) {
            automata.kinalatListazas();

            System.out.println("1. Pénz bedobása");
            System.out.println("2. Vásárlás (Gomb kiválasztása)");
            System.out.println("3. Pénz visszakérése");
            System.out.println("4. Kilépés");
            System.out.print("Mit teszel? ");

            int menu = scanner.nextInt();

            if (menu == 4) break;

            if (menu == 1) {
                System.out.print("Mennyit dobsz be? (50, 100, 200, 500): ");
                int penz = scanner.nextInt();
                automata.penzBedobas(penz);

            } else if (menu == 2) {
                System.out.print("Melyik terméket kéred? (Írd be a számát): ");
                int gomb = scanner.nextInt();

                // Itt hívjuk meg a te logikádat!
                automata.vasarlas(gomb);

            } else if (menu == 3) {
                automata.penzVissza();
            }
        }
    }
}