package gyakorlas.OkosOtthon;

public class Lampa implements Kapcsolhato {
    private boolean vilagit = false;

    @Override
    public void beKapcsol() {
        vilagit = true;
        System.out.println("💡 Lámpa: Fényár van!");
    }

    @Override
    public void kiKapcsol() {
        vilagit = false;
        System.out.println("🌑 Lámpa: Sötétség...");
    }
}
