package gyakorlas.Raktar;

public class Termek {
    private String nev;
    private int ar;
    private int keszlet; // Hány darab van belőle

    public Termek(String nev, int ar, int keszlet) {
        this.nev = nev;
        this.ar = ar;
        this.keszlet = keszlet;
    }

    // --- LOGIKA ---

    // Visszaadja, hogy sikerült-e az eladás (van-e elég áru)
    public boolean eladas(int darab) {
        if (darab <= 0) return false;

        if (this.keszlet >= darab) {
            this.keszlet -= darab; // Csökkentjük a készletet
            return true; // Siker!
        } else {
            return false; // Nincs elég a raktáron
        }
    }

    public void feltoltes(int darab) {
        if (darab > 0) {
            this.keszlet += darab;
        }
    }

    // --- GETTEREK & TOSTRING ---

    public String getNev() {
        return nev;
    }

    public int getAr() {
        return ar;
    }

    public int getKeszlet() {
        return keszlet;
    }

    @Override
    public String toString() {
        // Formázott kiírás: Az ár után "Ft", a készlet után "db"
        return String.format("%s - %d Ft (Készlet: %d db)", nev, ar, keszlet);
    }
}
