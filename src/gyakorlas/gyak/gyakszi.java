package gyakorlas.gyak;


import java.util.Arrays;
import java.util.List;

class Router {
    public String model;       // Bárki láthatja
    private String adminPass;  // CSAK a Router osztályon belül látszik

    public Router(String model, String pass) {
        this.model = model;
        this.adminPass = pass;
    }


    public String getadminPass() {
        return adminPass;
    }

    // Ez egy "kapu" (Getter), amin keresztül biztonságosan lekérhetjük a jelszót
    public String getPasswordSafe() {
        return "A jelszó titkosított, de a modell: " + model;
    }
}

class CiscoRouter extends Router implements Connectable {
    public int iosVersion;

    public CiscoRouter(String model, String pass, int iosVersion) {
        super(model, pass);
        this.iosVersion = iosVersion;
    }


    @Override
    public String getPasswordSafe() {
        return "Cisco Specifikus Info: " + model + "IOS: " + iosVersion + ")";
    }

    @Override
    public void connectToCloud() {
        System.out.println("Connecting to Cisco Meraki Dashboard...");
    }


}


public class gyakszi {
    public static void main(String[] args) {


        // Mit ír ki az IntelliJ pirossal?
        CiscoRouter c1 = new CiscoRouter("2911", "Secret123", 15);
        System.out.println(c1.model);
        System.out.println(c1.getadminPass());
        Router r2 = new CiscoRouter("9300-Switch", "Cisco123", 17);

        List<Router> myNetwork = Arrays.asList(
                new Router("ISR", "pass1"),
                new Router("Catalyst-9300", "pass2"),
                new Router("Mikro", "pass3"),
                new Router("Juniper-Edge", "pass4")
        );

        List<String> longNames = myNetwork.stream()
                .filter(r -> r.model.length() > 5)  // Csak a hosszú nevűek
                .map(r -> r.model)
                .map(String::toUpperCase)// Csak a nevekre vagyunk kíváncsiak (Routerből String lesz)
                .toList();

        System.out.println("Hosszú nevű eszközeink: " + longNames);

    }
}

