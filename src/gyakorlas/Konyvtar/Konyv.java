package gyakorlas.Konyvtar;

public class Konyv {
    private String cim;
    private String szerzo;

    public Konyv(String cim, String szerzo) {
        this.cim = cim;
        this.szerzo = szerzo;
    }

    @Override
    public String toString() {
        return "\"" + cim + "\" - " + szerzo;
    }
}
