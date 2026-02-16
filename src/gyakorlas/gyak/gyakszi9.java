package gyakorlas.gyak;

import java.util.*;

public class gyakszi9 {

    public static void main(String[] args) {

        List<Device> networkMap = Arrays.asList(
                new Device("192.168.1.1", Arrays.asList("HTTP", "HTTPS")),
                new Device("192.168.1.50", Arrays.asList("HTTP", "SSH", "SNMP")),
                new Device("10.0.0.5", Arrays.asList("FTP", "HTTP")),
                new Device("10.0.0.10", Arrays.asList("HTTPS")),
                new Device("172.16.0.100", Arrays.asList("SSH", "FTP"))
        );

        List<String> secureProblem = networkMap.stream()
                .filter(p -> p.services.contains("SSH") || p.services.contains("FTP"))
                .map(p -> p.ip)
                .toList();

        List<String> allServices = networkMap.stream()
                .flatMap(device -> device.services.stream()) // Minden eszköz szolgáltatás-listáját stream-mé alakítjuk
                .distinct() // Kiszedjük az ismétlődéseket (pl. a HTTP több helyen is van)
                .sorted()   // Csak hogy szép legyen a sorrend (opcionális)
                .toList();

        System.out.println("Biztonsági kockázatot jelentő IP-k: " + secureProblem);
        System.out.println("A hálózaton észlelt összes egyedi szolgáltatás: " + allServices);

        System.out.println("Problémás IP címek: \n");
        secureProblem.forEach(System.out::println);
        System.out.println("\nÖsszes szolgáltatás: \n");
        allServices.forEach(System.out::println);

    }
}

class Device {
    String ip;
    List<String> services; // pl. "HTTP", "SSH", "HTTPS", "FTP"

    Device(String ip, List<String> services) {
        this.ip = ip;
        this.services = services;
    }

    @Override
    public String toString() {
        return ip + " " + services;
    }
}

