package OOP;

import java.util.Random;
import java.util.Scanner;

public class Szamkitalalos {

    public void jatekInditas() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int megoldas = random.nextInt(100) + 1;
        int tipp = 0;
        int probalkozas = 0;

        System.out.println("Gondoltam egy számra, tippelj:");

        while (tipp != megoldas) {
            tipp = scanner.nextInt();
            probalkozas++;

            if (tipp > megoldas) {
                System.out.println("Kisebb a szám");
            } else if (tipp < megoldas) {
                System.out.println("Nagyobb a szám");
            } else if (tipp == megoldas) {
                System.out.println("Kitaláltad :) Ennyi próbálkozásból: " + probalkozas);
                break;
            }
        }
    }
}
