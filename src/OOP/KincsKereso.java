package OOP; // Ha kell...

import java.util.Random;
import java.util.Scanner;

public class KincsKereso {

    public void jatekInditas() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // 10 mező van összesen
        int palyaMeret = 10;
        // A gép elrejti a kincset (1 és 10 között)
        int kincsHelye = random.nextInt(palyaMeret) + 1;

        int elet = 5; // Ennyi ásásod van

        System.out.println("\n--- 🏴‍☠️ KINCSKERESŐ 🏴‍☠️ ---");
        System.out.println("Elrejtettem egy kincset az 1-" + palyaMeret + " mezők valamelyikén.");
        System.out.println("Neked " + elet + " ásásod van, hogy megtaláld!");

        // Játék ciklus
        while (elet > 0) {
            System.out.print("\nHol ássunk? (1-" + palyaMeret + "): ");
            int tipp = scanner.nextInt();

            if (tipp == kincsHelye) {
                System.out.println("🎉 GRATULÁLOK! Megtaláltad a kincsesládát!");
                break;
            } else {
                elet--;
                System.out.println("❌ Üres gödör... még " + elet + "ásásod maradt!");
                if (Math.abs(kincsHelye - tipp) <= 2) {
                    System.out.println("🔥 Forró nyomon vagy!");
                } else {
                    System.out.println("Hideg...❄  ");
                }
            }


        }


        // Ha elfogyott az élet, és még mindig a ciklus után vagyunk (nem léptünk ki break-kel)
        // Akkor sajnos vesztettél. De hogyan döntjük el?
        // Trükk: Ha az elet == 0, akkor biztosan vesztett.
        if (elet == 0) {
            System.out.println("\n💀 A kalózkapitány elkapott! A kincs itt volt: " + kincsHelye);
        }
        System.out.println("--------------------------\n");
    }

}
