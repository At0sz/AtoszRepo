package gyakorlas.BankApp;


public class Bankszamla {
    private String tulajdonos;
    private String pinKod;
    private int egyenleg;

    public Bankszamla(String tulajdonos, String pinKod, int kezdoEgyenleg) {
        this.tulajdonos = tulajdonos;
        this.pinKod = pinKod;
        this.egyenleg = kezdoEgyenleg;
    }

    // --- FELADAT 1: Írd meg a PIN ellenőrzést! ---
    // Igazat adjon vissza, ha a megadott pin megegyezik a tárolttal!
    public boolean pinEllenorzes(String megadottPin) {
        if (pinKod.equals(megadottPin)) {
            return true;
        } else {
            return false;
        }
    }

    // --- FELADAT 2: Pénz felvét ---
    // Ha van elég pénz, vond le és adj vissza igazat.
    // Ha nincs, adj vissza hamisat.
    public boolean penzFelvet(int osszeg) {
        if (egyenleg >= osszeg) {
            egyenleg -= osszeg;
            return true;
        } else {
            return false;
        }
    }

    public void befizet(int osszeg) {
        this.egyenleg += osszeg;
    }

    public int getEgyenleg() {
        return egyenleg;
    }

    public String getTulajdonos() {
        return tulajdonos;
    }
}