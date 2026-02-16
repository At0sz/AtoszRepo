package gyakorlas.mozi;

public class Terem {
    private int sorok;
    private int szekekSoronkent;
    private char[][] szekek; // 2D Tömb: [Sor][Oszlop]

    public Terem(int sorok, int szekekSoronkent) {
        this.sorok = sorok;
        this.szekekSoronkent = szekekSoronkent;
        this.szekek = new char[sorok][szekekSoronkent];

        // Kezdetben minden szék szabad ('O')
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < szekekSoronkent; j++) {
                szekek[i][j] = 'O';
            }
        }
    }

    public void teremRajzolas() {
        System.out.println("\n   [ VÁSZON ]");
        System.out.print("   ");
        for (int k = 1; k <= szekekSoronkent; k++) {
            System.out.print(k + " ");
        }
        System.out.println();

        for (int i = 0; i < sorok; i++) {
            System.out.print((i + 1) + ". ");
            for (int j = 0; j < szekekSoronkent; j++) {
                System.out.print(szekek[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Jelmagyarázat: O = Szabad, X = Foglalt\n");
    }

    public boolean foglal(int sor, int szek) {
        int rIndex = sor - 1;
        int cIndex = szek - 1;

        if (rIndex < 0 || rIndex >= sorok || cIndex < 0 || cIndex >= szekekSoronkent) {
            System.out.println("Hiba: Ilyen szék nem létezik!");
            return false;
        }

        if (szekek[rIndex][cIndex] == 'X') {
            System.out.println("Hiba: Ez a szék már foglalt!");
            return false;
        }

        szekek[rIndex][cIndex] = 'X';
        return true;
    }

    // --- FELADAT: Írd meg a visszaváltás végét! ---
    public boolean lemondas(int sor, int szek) {
        // 1. Átváltás indexekre
        int rIndex = sor - 1;
        int cIndex = szek - 1;

        // 2. Ellenőrzés: Létezik a szék? (Ezt már megírtam neked)
        if (rIndex < 0 || rIndex >= sorok || cIndex < 0 || cIndex >= szekekSoronkent) {
            System.out.println("Hiba: Ilyen szék nem létezik!");
            return false;
        }

        if (szekek[rIndex][cIndex] == 'O') {
            System.out.println("Hiba, ez nem foglalt hely!");
            return false;
        } else {
            szekek[rIndex][cIndex] = 'O';
            return true;
        }
        // 3. Ellenőrzés és Visszaváltás (EZT ÍRD MEG TE!)
        // Vizsgáld meg: szekek[rIndex][cIndex] == 'O' ?
        // Ha igen -> Hiba, ez már üres.
        // Ha nem ('X') -> Állítsd vissza 'O'-ra és return true!


    }

    public int szabadHelyekSzama() {
        int szabad = 0;
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < szekekSoronkent; j++) {
                if (szekek[i][j] == 'O') {
                    szabad++;
                }
            }
        }
        return szabad;
    }
}