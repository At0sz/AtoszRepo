package gyakorlas.OkosOtthonV2;

import java.util.ArrayList;
import java.util.List;

class KozpontiVezetlek {

    // Polimorfizmus! Ebben a listában tárolhatunk OkosLampa ÉS OkosFutes objektumokat is!
    List<Vezerelheto> eszkozok = new ArrayList<>();

    public void eszkoztHozzaad(Vezerelheto eszkoz) {
        eszkozok.add(eszkoz);
    }

    // Cél: Be kell járni az 'eszkozok' listát (for-each ciklussal!)
    // és minden elemen meg kell hívni a .bekapcsol() metódust.
    public void mindetBekapcsol() {
        System.out.println("\n--- Mindent BEKAPCSOL ---");
        // HIÁNYZIK A KÓD: for-each ciklus a lista bejárásához
        for (Vezerelheto eszkoz : eszkozok) {
            eszkoz.bekapcsol();
        }
    }

    // A Te Feladatod: Hasonlóan, de minden eszközön a .statuszKiir() metódust kell hívni.
    public void osszesStatusz() {
        System.out.println("\n--- Jelenlegi Státuszok ---");
        // HIÁNYZIK A KÓD: for-each ciklus a lista bejárásához és a .statuszKiir() hívásához
        for (Vezerelheto eszkoz : eszkozok) {
            eszkoz.statuszKiir();
        }
    }

    public void osszesKikapcsol() {
        System.out.println("\n--- Mindent KIKAPCSOL ---");
        for (Vezerelheto eszkoz : eszkozok) {
            eszkoz.kikapcsol();
        }
    }

    public List<Vezerelheto> getEszkozok() {
        return eszkozok;
    }

}
