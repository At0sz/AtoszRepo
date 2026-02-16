package gyakorlas.Hotel;

public class Szoba {
    private int szobaszam;
    private int arEjszakankent;
    private int agyakSzama; // Pl. 2 ágyas
    private String vendegNeve; // Ha null, akkor üres! Ha van név, akkor foglalt.

    public Szoba(int szobaszam, int arEjszakankent, int agyakSzama) {
        this.szobaszam = szobaszam;
        this.arEjszakankent = arEjszakankent;
        this.agyakSzama = agyakSzama;
        this.vendegNeve = null; // Kezdetben üres
    }

    // --- LOGIKA ---

    // Megmondja, hogy szabad-e a szoba
    public boolean isSzabad() {
        return vendegNeve == null;
    }

    // Lefoglaljuk a szobát
    public void lefoglal(String nev) {
        this.vendegNeve = nev;
    }

    // Kijelentkezéskor töröljük a nevet
    public void felszabadit() {
        this.vendegNeve = null;
    }

    // --- GETTEREK ---
    public int getSzobaszam() {
        return szobaszam;
    }

    public int getArEjszakankent() {
        return arEjszakankent;
    }

    public String getVendegNeve() {
        return vendegNeve;
    }

    @Override
    public String toString() {
        String statusz = isSzabad() ? "✅ Szabad" : "❌ Foglalt (" + vendegNeve + ")";
        return "Szoba " + szobaszam + " (" + agyakSzama + " ágyas) - " + arEjszakankent + " Ft/éj | " + statusz;
    }
}