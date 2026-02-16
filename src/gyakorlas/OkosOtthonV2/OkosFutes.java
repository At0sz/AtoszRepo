package gyakorlas.OkosOtthonV2;

public class OkosFutes implements Vezerelheto {

    private int homerseklet = 20;

    public OkosFutes(int homerseklet) {
        this.homerseklet = homerseklet;
    }

    public void bekapcsol() {
        System.out.println("Fűtés bekapcsolva, aktuális hőmérséklet: " + homerseklet + " celsius fok");
    }

    public void kikapcsol() {
        System.out.println("Fűtés kikapcsolva.");
    }

    public void statuszKiir() {
        System.out.println("Jelenlegi hőmérséklet: " + homerseklet);
    }

}
