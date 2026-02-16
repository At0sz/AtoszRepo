package gyakorlas.Pizzeria;

import java.util.ArrayList;

public class Pizza {
    private String nev;
    private Meret meret; // Itt használjuk az Enumot típusként!
    private ArrayList<String> feltetek = new ArrayList<>();

    public Pizza(String nev, Meret meret) {
        this.nev = nev;
        this.meret = meret;
    }

    public void feltetHozzaad(String feltet) {
        feltetek.add(feltet);
    }

    // --- FELADAT: Számold ki a pizza árát! ---
    public int arSzamitas() {
        // 1. Kérd le a méret alapárát: this.meret.getAr()
        // 2. Minden extra feltét kerüljön 200 Ft-ba.
        //    (A feltetek.size() megmondja, hány darab van)
        // 3. Add össze és add vissza (return)!

        int alap = this.meret.getAr();
        alap += (this.feltetek.size() * 200);
        return alap;
    }

    @Override
    public String toString() {
        return nev + " (" + meret + ") - Feltétek: " + feltetek + " => Ár: " + arSzamitas() + " Ft";
    }
}