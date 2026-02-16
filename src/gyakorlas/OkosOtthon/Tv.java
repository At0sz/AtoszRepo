package gyakorlas.OkosOtthon;

public class Tv implements Kapcsolhato {
    private boolean bekapcsolva = false;

    @Override
    public void beKapcsol() {
        bekapcsolva = true;
        System.out.println("\uD83D\uDCFA TV: Netflix indul...");
    }

    @Override
    public void kiKapcsol() {
        bekapcsolva = false;
        System.out.println("\uD83D\uDE34 TV: Kikapcsolva.");
    }

}
