package gyakorlas.Egyetem;

import java.util.ArrayList;

public class Kurzus {
    private String nev;
    private int maxLetszam;
    // Itt tároljuk, kik vették fel a tárgyat!
    private ArrayList<Diak> beiratkozottDiakok = new ArrayList<>();

    public Kurzus(String nev, int maxLetszam) {
        this.nev = nev;
        this.maxLetszam = maxLetszam;
    }

    // --- FELADAT 1: JAVÍTOTT Felvétel ---
    public boolean diakFelvetele(Diak ujDiak) {
        // 1. Van még hely?
        if (beiratkozottDiakok.size() >= maxLetszam) {
            return false; // Betelt
        }

        // 2. Már rajta van a listán? (Végigmegyünk és keresünk)
        for (Diak d : beiratkozottDiakok) {
            // FONTOS: Stringet .equals()-szal hasonlítunk!
            if (d.getNeptunKod().equals(ujDiak.getNeptunKod())) {
                return false; // Már felvette, hiba!
            }
        }

        // 3. Ha eljutottunk idáig, akkor van hely ÉS még nem vette fel.
        // Mehet a hozzáadás!
        beiratkozottDiakok.add(ujDiak);
        return true;
    }

    // --- FELADAT 2: JAVÍTOTT Leadás ---
    public boolean diakLeadasa(String neptunKod) {
        for (int i = 0; i < beiratkozottDiakok.size(); i++) {
            // Itt is .equals() kell!
            if (beiratkozottDiakok.get(i).getNeptunKod().equals(neptunKod)) {
                beiratkozottDiakok.remove(i);
                return true; // Megtaláltuk és töröltük
            }
        }
        return false; // Nem volt ilyen diák a listában
    }

    // Listázás (már kész, segít a tesztelésben)
    public void listazResztvevok() {
        System.out.println("--- " + nev + " résztvevői (" + beiratkozottDiakok.size() + "/" + maxLetszam + ") ---");
        if (beiratkozottDiakok.isEmpty()) {
            System.out.println("(Üres)");
        } else {
            for (Diak d : beiratkozottDiakok) {
                System.out.println("- " + d);
            }
        }
    }

    public String getNev() {
        return nev;
    }

    public int getSzabadHelyek() {
        return maxLetszam - beiratkozottDiakok.size();
    }

    @Override
    public String toString() {
        return nev + " [Helyek: " + beiratkozottDiakok.size() + "/" + maxLetszam + "]";
    }
}