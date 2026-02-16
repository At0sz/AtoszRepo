package gyakorlas.Automata;

import java.util.HashMap;
import java.util.Map;

public class ItalAutomata {

    // A gép "zsebe" (amit a vevő épp bedobált)
    private int bedobottPenz = 0;

    // A kínálat: Gomb Száma (Integer) -> Termék
    private HashMap<Integer, Termek> kinalat = new HashMap<>();

    public ItalAutomata() {
        // Feltöltjük a gépet áruval
        kinalat.put(1, new Termek("Cola", 450, 5));
        kinalat.put(2, new Termek("Fanta", 450, 3));
        kinalat.put(3, new Termek("Víz", 250, 10));
        kinalat.put(4, new Termek("Kávé", 300, 2));
    }

    public void penzBedobas(int osszeg) {
        if (osszeg > 0) {
            bedobottPenz += osszeg;
            System.out.println("Bedobva: " + osszeg + " Ft. (Jelenlegi egyenleg: " + bedobottPenz + " Ft)");
        }
    }

    public void kinalatListazas() {
        System.out.println("\n--- KÍNÁLAT ---");
        for (Map.Entry<Integer, Termek> tetel : kinalat.entrySet()) {
            System.out.println("[" + tetel.getKey() + "] " + tetel.getValue());
        }
        System.out.println("-----------------");
        System.out.println("BEDOBOTT PÉNZ: " + bedobottPenz + " Ft\n");
    }

    // --- FELADAT: ÍRD MEG A VÁSÁRLÁST! ---
    public void vasarlas(int gombSzama) {
        // 1. Ellenőrizd: Létezik ilyen gomb? (kinalat.containsKey)
        if (!kinalat.containsKey(gombSzama)) {
            System.out.println("Hibás gomb!");
            return;
        }

        // Lekérjük a terméket
        Termek valasztott = kinalat.get(gombSzama);
        if (!valasztott.vanKeszleten()) {
            System.out.println("Sajnos elfogyott.");
            return;
        } else {
            if (bedobottPenz >= valasztott.getAr()) {
                System.out.println("Sikeres vásárlás.");
                int visszajaro = bedobottPenz - valasztott.getAr();
                valasztott.kiad();
                bedobottPenz = 0;
                System.out.println("Kiadva: " + valasztott.getNev() + ". Visszajáró: " + visszajaro + " Ft");
            } else {
                System.out.println("Kevés a pénz! Még szükséges: " + (valasztott.getAr() - bedobottPenz));
            }


        }
        // 2. Ellenőrizd: Van-e készleten? (valasztott.vanKeszleten())
        // Ha nincs, írd ki: "Sajnos elfogyott." és return.


        // 3. Ellenőrizd: Elég-e a bedobott pénz? (bedobottPenz >= ar)
        // Ha nem elég, írd ki: "Kevés a pénz! Még kell: ..."


        // 4. HA MINDEN OK (Sikeres vásárlás):
        //    - Számold ki a visszajárót! (bedobott - ár)
        //    - Csökkentsd a készletet! (valasztott.kiad())
        //    - Nullázd le a bedobott pénzt! (Mert elköltötte)
        //    - Írd ki: "Kiadva: [Termék neve]. Visszajáró: [Visszajáró] Ft."


    }

    // Pénz visszaadása (ha meggondolta magát)
    public void penzVissza() {
        System.out.println("Visszadva: " + bedobottPenz + " Ft");
        bedobottPenz = 0;
    }
}