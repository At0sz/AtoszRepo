package gyakorlas.Egyetem;

public class Diak {
    private String nev;
    private String neptunKod;

    public Diak(String nev, String neptunKod) {
        this.nev = nev;
        this.neptunKod = neptunKod;
    }

    public String getNev() {
        return nev;
    }

    public String getNeptunKod() {
        return neptunKod;
    }

    @Override
    public String toString() {
        return nev + " (" + neptunKod + ")";
    }
}