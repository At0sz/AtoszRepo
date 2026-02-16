package gyakorlas.Pizzeria;

import java.util.ArrayList;
import java.util.Scanner;

public class Rendeles {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pizza> kosar = new ArrayList<>();

        System.out.println("--- 🍕 JAVA PIZZÉRIA 🍕 ---");

        while (true) {
            System.out.println("\n1. Pizza rendelése");
            System.out.println("2. Kosár megtekintése (Fizetés)");
            System.out.println("3. Kilépés");
            System.out.print("Válassz: ");

            int menu = scanner.nextInt();
            scanner.nextLine();

            if (menu == 3) break;

            if (menu == 1) {
                System.out.print("Pizza neve (pl. Songoku): ");
                String nev = scanner.nextLine();

                System.out.println("Mekkora legyen? (1=Kicsi, 2=Normál, 3=Családi)");
                int meretValasztas = scanner.nextInt();
                scanner.nextLine();

                // ENUM kiválasztása szám alapján
                Meret kivalasztottMeret = Meret.NORMAL; // Alapértelmezett
                if (meretValasztas == 1) kivalasztottMeret = Meret.KICSI;
                else if (meretValasztas == 2) kivalasztottMeret = Meret.NORMAL;
                else if (meretValasztas == 3) kivalasztottMeret = Meret.CSALADI;

                // Létrehozzuk a pizzát
                Pizza ujPizza = new Pizza(nev, kivalasztottMeret);

                // Feltétek hozzáadása
                while (true) {
                    System.out.print("Kérsz rá plusz feltétet? (Írd be vagy üres Enter a végéhez): ");
                    String feltet = scanner.nextLine();
                    if (feltet.isEmpty()) break; // Ha üreset nyom, kilép a feltét módból

                    ujPizza.feltetHozzaad(feltet);
                }

                kosar.add(ujPizza);
                System.out.println("Pizza a kosárba téve!");
            } else if (menu == 2) {
                System.out.println("\n--- A KOSARAD TARTALMA ---");
                int vegosszeg = 0;

                // FELADAT: Menj végig a 'kosar' listán!
                // 1. Írd ki a pizzákat (System.out.println(p)).
                // 2. Add hozzá az árukat a vegosszeghez (p.arSzamitas()).

                for (Pizza p : kosar) {
                    System.out.println(p);
                    vegosszeg += p.arSzamitas();
                    // Add hozzá az összeget itt!
                }

                System.out.println("--------------------------");
                System.out.println("VÉGÖSSZEG: " + vegosszeg + " Ft");
            }
        }
    }
}
