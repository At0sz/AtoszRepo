package gyakorlas.Autokolcsonzo;

public class Auto {
    private String rendszam;
    private String tipus;
    private int napiAr;
    private boolean elerheto; // true = bent van, false = ki van adva

    public Auto(String rendszam, String tipus, int napiAr) {
        this.rendszam = rendszam;
        this.tipus = tipus;
        this.napiAr = napiAr;
        this.elerheto = true; // Alapból minden autó szabad
    }

    // --- LOGIKA ---

    // Megpróbáljuk kivenni.
    // Ha sikerül (mert szabad volt), átállítjuk hamisra és igazat adunk vissza.
    // Ha nem sikerül (mert már kint van), hamist adunk vissza.
    public boolean kivesz() {
        if (this.elerheto) {
            this.elerheto = false;
            return true;
        } else {
            return false;
        }
    }

    // Visszahozzuk az autót -> újra szabad
    public void visszahoz() {
        this.elerheto = true;
    }

    // --- GETTEREK ---
    public String getRendszam() {
        return rendszam;
    }

    public String getTipus() {
        return tipus;
    }

    public int getNapiAr() {
        return napiAr;
    }

    public boolean isElerheto() {
        return elerheto;
    }

    @Override
    public String toString() {
        String statusz = elerheto ? "✅ Szabad" : "❌ Kiadva";
        return String.format("[%s] %s (%d Ft/nap) - %s", rendszam, tipus, napiAr, statusz);
    }
}
