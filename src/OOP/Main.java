package OOP;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean futAProgram = true;

        System.out.println("👋 Üdvözöllek a JAVA ÁRKÁD JÁTÉKTEREMBEN! 👋");

        // Végtelen ciklus a menühöz
        while (futAProgram) {
            System.out.println("\n================ MENÜ ================");
            System.out.println("1. 🎲 Számkitaláló (Gondoltam egy számra...)");
            System.out.println("2. 💀 Akasztófa (Hangman)");
            System.out.println("3. ✂️ Kő-Papír-Olló");
            System.out.println("4. 🎰 Félkarú Rabló (Slot Machine)");
            System.out.println("5. 🃏 BlackJack");
            System.out.println("6. 🔭 Kincs Kereső");
            System.out.println("7. ❌⭕ Amőba");
            System.out.println("8. 🚪 KILÉPÉS");
            System.out.print("Válassz egy játékot (1-8): ");

            int valasztas = scanner.nextInt();

            System.out.println("--------------------------------------");

            if (valasztas == 1) {
                // 1. Számkitaláló
                Szamkitalalos jatek1 = new Szamkitalalos();
                jatek1.jatekInditas();

            } else if (valasztas == 2) {
                // 2. Akasztófa
                Hangman jatek2 = new Hangman();
                jatek2.jatekInditas();

            } else if (valasztas == 3) {
                // 3. Kő-Papír-Olló
                KoPapirOllo jatek3 = new KoPapirOllo();
                jatek3.jatekInditas();

            } else if (valasztas == 4) {
                // 4. Félkarú Rabló
                FelkaruRablo jatek4 = new FelkaruRablo();
                jatek4.jatekInditas();

            } else if (valasztas == 5) {
                // 5. BlackJack
                BlackJack jatek5 = new BlackJack();
                jatek5.jatekInditas();

            } else if (valasztas == 6) {
                // 6. KincsKereso
                KincsKereso jatek6 = new KincsKereso();
                jatek6.jatekInditas();

            } else if (valasztas == 7) {
                // 7. Amőba
                Amoba jatek7 = new Amoba();
                jatek7.jatekInditas();

            } else if (valasztas == 8) {
                // 5. Kilépés
                System.out.println("Viszlát! Gyere vissza máskor is!");
                futAProgram = false; // Ez állítja le a ciklust

            } else {
                System.out.println("Hibás választás, próbáld újra!");
            }
        }
    }
}

