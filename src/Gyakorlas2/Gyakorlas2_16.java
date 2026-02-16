package Gyakorlas2;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gyakorlas2_16 {
    public static void main(String[] args) {
        System.out.println();

        List<NetworkDevice16> devices = new ArrayList<>();
        devices.add(new NetworkDevice16("R1", "10.0.0.1", "ROUTER", Arrays.asList("10.0.0.2", "10.0.0.5")));
        // 1. Alap routerek (működő kapcsolatokkal)
        devices.add(new NetworkDevice16("R1", "10.0.0.1", "ROUTER", Arrays.asList("10.0.0.2", "10.0.0.5")));
        devices.add(new NetworkDevice16("R2", "10.0.0.2", "ROUTER", Arrays.asList("10.0.0.1", "10.0.0.10")));
        devices.add(new NetworkDevice16("R3", "10.0.0.3", "ROUTER", Arrays.asList("10.0.0.1", "10.0.0.10")));

        // 2. Egy switch, aminek van egy "Ghost" szomszédja (a 8.8.8.8 nincs a devices listában!)
        devices.add(new NetworkDevice16("SW-Core-01", "10.0.0.5", "SWITCH", Arrays.asList("10.0.0.1", "8.8.8.8")));

        // 3. Egy gyanús eszköz, aminek üres a szomszéd listája (Empty list)
        devices.add(new NetworkDevice16("SW-Access-01", "10.0.0.10", "SWITCH", new ArrayList<>()));

        // 4. A "BOMBA": Egy eszköz, aminek NULL a szomszéd listája (Erre fogsz elszállni NullPointerExceptionnel, ha nem figyelsz!)
        devices.add(new NetworkDevice16("Bad-Config-Router", "10.0.0.99", "ROUTER", null));

        // 5. Még egy router a statisztikához
        devices.add(new NetworkDevice16("R3-Backup", "10.0.0.20", "ROUTER", Arrays.asList("10.0.0.1")));


        System.out.println("--- Topológia Ellenőrzés ---");
        // 1. FELADAT: Biztonságos feldolgozás (Null-check és Try-Catch)
        // Menj végig az eszközökön, és írd ki a nevüket, de ha valamelyik mező null,
        // ne szálljon el a program (NullPointerException)!

        devices.forEach(device -> {
            try{
                System.out.println(device.getHostname());
            }catch(Exception e){
                System.out.println("Hibás");
            }
        });


        // 2. FELADAT: Stream-mágia (A "Szomszéd-vadász")
        // Gyűjtsd ki egy új List<String>-be az ÖSSZES szomszéd IP-t az összes eszközről!
        // Tipp: Használd a .flatMap(d -> d.getNeighbors().stream())-et!

        List<String> neighborIps = devices.stream()
                .filter(d -> d.getNeighbors() != null)
                .flatMap(d-> d.getNeighbors().stream())
                .toList();

        System.out.println("\n--- Összes szomszédos ip cím ---");
        neighborIps.forEach(System.out::println);

        // 3. FELADAT: Statisztika
        // Hány darab ROUTER típusú eszköz van a hálózatban? (Stream + filter + count)

        long routerCount = devices.stream()
                .filter(d->d.getType().equals("ROUTER"))
                .count();

        System.out.println("\nA Router tipusú eszközök száma: " + routerCount);

        // 4. FELADAT: A "Ghost" IP-k
        // Találd meg azokat az IP-ket a szomszédok között, amik NINCSENEK benne
        // a 'devices' lista fő IP-jei között! (Ez a hálózati hiba: ismeretlen szomszéd)

        // 1. Gyűjtsük ki az összes létező eszköz IP-jét egy listába
        List<String> knownIps = devices.stream()
                .map(NetworkDevice16::getIp) // Csak az IP mezőket szedjük ki
                .toList();

// 2. Szűrjük ki a szomszédok közül azokat, amik nincsenek a knownIps-ben
        List<String> ghostIps = neighborIps.stream()
                .filter(ip -> !knownIps.contains(ip)) // Ha NEM tartalmazza a lista az adott IP-t
                .distinct() // Az ismétlődéseket töröljük (pl. 8.8.8.8 csak egyszer kell)
                .toList();

        System.out.println("\nGhost IP-k észlelve: " + ghostIps);


    }
}

@Data
@AllArgsConstructor
class NetworkDevice16 {
    private String hostname;
    private String ip;
    private String type; // ROUTER vagy SWITCH
    private List<String> neighbors; // Szomszédos IP-k listája
}