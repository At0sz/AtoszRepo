package gyakorlas.OkosOtthonV2;

public class Main {
    public static void main(String[] args) {

        KozpontiVezetlek vezetek = new KozpontiVezetlek();

        // Polimorfizmus: Két különböző típusú objektumot tárolunk az Interface Listában
        vezetek.eszkoztHozzaad(new OkosLampa());
        vezetek.eszkoztHozzaad(new OkosFutes(22));
        vezetek.eszkoztHozzaad(new OkosLampa());

        vezetek.mindetBekapcsol();

        // Utána egyes eszközökön kikapcsolunk
//        Vezerelheto elso = lista.get(0);

        vezetek.osszesStatusz();
    }
}