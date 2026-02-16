package gyakorlas.Pizzeria;

public enum Meret {
    // 1. Felsoroljuk a lehetséges értékeket és az árukat
    KICSI(1200),
    NORMAL(1600),
    CSALADI(3500);

    // 2. Az Enum is egy osztály, lehetnek mezői!
    private final int alapAr;

    // Konstruktor (privát, csak az enum használja)
    Meret(int ar) {
        this.alapAr = ar;
    }

    // Getter az árhoz
    public int getAr() {
        return alapAr;
    }
}