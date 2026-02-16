package gyakorlas.mozi;

public class Film {
    private String cim;
    private String mufaj;
    private int ar;

    public Film(String cim, String mufaj, int ar) {
        this.cim = cim;
        this.mufaj = mufaj;
        this.ar = ar;
    }

    public String getCim() {
        return cim;
    }

    public int getAr() {
        return ar;
    }

    @Override
    public String toString() {
        return cim + " (" + mufaj + ") - " + ar + " Ft";
    }
}