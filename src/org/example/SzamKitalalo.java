package org.example;

import java.util.Random;
import java.util.Scanner;

public class SzamKitalalo {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        int randomSzam = rand.nextInt(100) + 1;
        int tipp;
        int probalkozas = 0;

        System.out.println("Gondoltam egy számra 1 és 100 között. Találd ki!");

        do {
            System.out.print("Tippelj: ");
            tipp = sc.nextInt();
            probalkozas++;

            if (tipp < randomSzam) {
                System.out.println("Túl kicsi!");
            } else if (tipp > randomSzam) {
                System.out.println("Túl nagy!");
            } else {
                System.out.println("Eltaláltad! 🎯 (" + probalkozas + " próbálkozásból)");
            }
        } while (tipp != randomSzam);

        sc.close();
    }
}









