package Gemini;

public class Main {
    private static String Ati;

    public static void main(String[] args) {

        // 1. Létrehozod az objektumot.
        Szamla atiSzamlaja = new Szamla(); // Itt kell inicializálni a new kulcsszóval!

        // 2. Beállítod a nevet a setter metódussal (a metódus fut, de nem ad vissza semmit)
        atiSzamlaja.setTulajdonosNeve("Attila Bánk"); // Közvetlen hívás!

        // 3. Lekéred a beállított értéket a getter metódussal
        String nev = atiSzamlaja.getTulajdonosNeve();

        System.out.println("Számla tulajdonosa: " + nev);
    }
}