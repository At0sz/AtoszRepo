package gyakorlas.OkosOtthonV2;

public class OkosLampa implements Vezerelheto {

    private boolean bekapcsolva = false;


    public void bekapcsol() {
        bekapcsolva = true;
        System.out.println("Lámpa be kapcsolva ✅");
    }


    public void kikapcsol() {
        bekapcsolva = false;
        System.out.println("Lámpa ki kapcsolva ❌");
    }


    public void statuszKiir() {
        if (bekapcsolva == true) {
            System.out.println("Be kapcsolva");
        } else {
            System.out.println("Ki kapcsolva");
        }
    }


}
