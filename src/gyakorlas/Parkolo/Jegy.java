package gyakorlas.Parkolo;

public class Jegy {
    private String rendszam;
    private int erkezesiOra; // Pl. 8, 14 (egyszerűsítve, egész órákban)
    private boolean vip; // Igaz, ha VIP helyen áll (drágább)

    public Jegy(String rendszam, int erkezesiOra, boolean vip) {
        this.rendszam = rendszam;
        this.erkezesiOra = erkezesiOra;
        this.vip = vip;
    }

    public String getRendszam() {
        return rendszam;
    }

    public int getErkezesiOra() {
        return erkezesiOra;
    }

    public boolean isVip() {
        return vip;
    }

    @Override
    public String toString() {
        String tipus = vip ? "VIP" : "Normál";
        return "[" + rendszam + "] Beérkezett: " + erkezesiOra + ":00 (" + tipus + ")";
    }
}