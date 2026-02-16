package gyakorlas.OkosOtthon;

public class Klima implements Kapcsolhato {
    private boolean hutes = false;

    @Override
    public void beKapcsol() {
        hutes = true;
        System.out.println("❄\uFE0F Klíma: Hűtés indul...");
    }

    @Override
    public void kiKapcsol() {
        hutes = false;
        System.out.println("❄\uFE0F Klíma: Hűtés lekapcsolás folyamatban...");
    }
}
