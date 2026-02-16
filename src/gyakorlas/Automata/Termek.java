package gyakorlas.Automata;

public class Termek {
    private String nev;
    private int ar;
    private int darab; // Hány darab van a gépben

    public Termek(String nev, int ar, int darab) {
        this.nev = nev;
        this.ar = ar;
        this.darab = darab;
    }

    // --- LOGIKA ---

    // Van még belőle?
    public boolean vanKeszleten() {
        return darab > 0;
    }

    // Kiadunk egyet (csökkentjük a készletet)
    public void kiad() {
        if (darab > 0) {
            darab--;
        }
    }

    // --- GETTEREK ---
    public String getNev() {
        return nev;
    }

    public int getAr() {
        return ar;
    }

    public int getDarab() {
        return darab;
    }

    @Override
    public String toString() {
        if (darab == 0) {
            return nev + " (ELFOGYOTT)";
        }
        return nev + " - " + ar + " Ft";
    }
}