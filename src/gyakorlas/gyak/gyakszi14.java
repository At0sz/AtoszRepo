package gyakorlas.gyak;

import java.util.*;
import java.util.stream.*;

public class gyakszi14 {
    static void main(String[] args) {

        List<String> rawData = Arrays.asList(
                "Router-01;192.168.1.1;24;DEVICE",
                "Edge-FW;10.0.0.254;8;FIREWALL",
                "Switch-Core;172.16.0.1;48;DEVICE"
        );

        List<NetworkDevice2> devices = rawData.stream()
                .map(line -> {
                    String[] p = line.split(";");
                    String name = p[0];
                    String ip = p[1];
                    int ports = Integer.parseInt(p[2]);
                    String type = p[3];

                    if (type.equals("FIREWALL")) {
                        Firewall fw = new Firewall(name, ip, ports);
                        fw.addRule(); // Adjunk is hozzá egy alap szabályt
                        return fw;
                    } else {
                        return new NetworkDevice2(name, ip, ports);
                    }
                })
                .toList();

        // Mindenkit kapcsoljunk be!
        devices.forEach(NetworkDevice2::powerOn);

        // Írassuk ki a riportot!
        //devices.forEach(d -> System.out.println(d.getInfo()));

        int allPorts = devices.stream().mapToInt(d -> d.ports).sum();

        System.out.println(allPorts);
        System.out.println("\n");

        List<String> rawData2 = Arrays.asList(
                "Router-01;192.168.1.1;24;DEVICE",
                "Bad-Switch;invalid_ip;48;DEVICE",      // HIBÁS IP
                "Edge-FW;10.0.0.254;8;FIREWALL",
                "Guest-Wifi;172.16.0.5;0;DEVICE",       // HIBÁS PORTSZÁM (0)
                "Hack-Box;10.0.0.99;12;UNKNOWN",        // ISMERETLEN TÍPUS
                "Core-Switch;10.0.0.1;128;DEVICE"
        );

        rawData2.stream()
                .map(line -> line.split(";"))
                .filter(p -> p[1].contains("."))
                .filter(p -> Integer.parseInt(p[2]) > 0)
                .filter(p -> p[3].equals("FIREWALL") || p[3].equals("DEVICE"))
                .map(p -> {
                    String name = p[0];
                    String ip = p[1];
                    int ports = Integer.parseInt(p[2]);
                    String type = p[3];

                    if (type.equals("FIREWALL")) {
                        Firewall fw = new Firewall(name, ip, ports);
                        fw.addRule();
                        fw.addRule();
                        fw.powerOn();
                        return fw;
                    } else {
                        NetworkDevice2 device = new NetworkDevice2(name, ip, ports);
                        device.powerOn();
                        return device;
                    }

                })
                .forEach(p -> System.out.println(p.getInfo()));


        List<String> rawData3 = Arrays.asList(
                "Sensor-01;150;ACTIVE",
                "Sensor-02;ERROR;ACTIVE",    // HIBÁS ADAT (Szöveg a szám helyett)
                "Sensor-03;200;INACTIVE",  // NEM AKTÍV
                "Sensor-04;abc;ACTIVE",      // MÉG EGY HIBÁS ADAT
                "Sensor-05;500;ACTIVE"
        );

        int activeSum = rawData3.stream()
                .map(line -> line.split(";"))
                .filter(p -> p.length == 3)
                .filter(p -> p[2].equals("ACTIVE"))
                .map(p -> {
                    try {
                        return Integer.parseInt(p[1]);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return 0;
                    }

                })
                .mapToInt(Integer::intValue).sum();

        System.out.println("\nÖsszesített forgalom: " + activeSum);


        List<String> rawData4 = Arrays.asList(
                "EUROPE;1000",
                "USA;2500",
                "EUROPE;500",
                "ASIA;3000",
                "USA;1200",
                "ASIA;INVALID", // Itt is van egy kis hiba!
                "EUROPE;200"
        );

        Map<String, Integer> stats = rawData4.stream()
                .map(line -> line.split(";"))
                .collect(Collectors.groupingBy(
                        p -> p[0], // Mi alapján csoportosítson? (Régió neve)
                        Collectors.summingInt(p -> {
                            try {
                                return Integer.parseInt(p[1]);
                            } catch (Exception e) {
                                return 0;
                            }// Itt jön a try-catch-es számmá alakításod!
                        })
                ));

        System.out.println("\n" + stats);

        List<String> rawData5 = Arrays.asList(
                "EUROPE;Router-01;45",
                "USA;Switch-01;80",
                "EUROPE;Router-02;95",  // <-- Ő a legterheltebb Európában
                "ASIA;Server-01;30",
                "USA;Switch-02;15",
                "ASIA;Server-02;70"    // <-- Ő a legterheltebb Ázsiában
        );

        Map<Object, Optional<String[]>> cleanList4 = rawData5.stream()
                .map(p -> p.split(";"))
                .filter(p -> p.length == 3)
                .collect(Collectors.groupingBy(
                        p -> p[0], // Régió
                        Collectors.maxBy(Comparator.comparingInt(p -> Integer.parseInt(p[2])))
                ));

        cleanList4.forEach((region, data) -> {
            data.ifPresent(d -> System.out.println(region + " legterheltebb: " + d[1] + " (" + d[2] + "%)"));
        });

        System.out.println("\n");

        List<String> rawData6 = Arrays.asList(
                "Switch-Alpha;5000;2018",
                "Router-Beta;1200;2022",
                "Server-Gamma;8000;2015",
                "AP-Delta;3000;2021",
                "Core-Epsilon;10000;2010"
        );

        rawData6.stream()
                .map(line -> line.split(";"))
                .filter(p -> Integer.parseInt(p[1]) > 4000 || Integer.parseInt(p[2]) < 2017)
                .sorted((a, b) -> Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1])))
                .forEach(p -> {
                    if (Integer.parseInt(p[1]) > 4000) {
                        System.out.println("Név: " + p[0] + " ,üzemidő: " + p[1] + " ,gyártási év: " + p[2] + " , Hiba:üzemidő!");
                    } else {
                        System.out.println("Név: " + p[0] + " ,üzemidő: " + p[1] + " ,gyártási év: " + p[2] + " , Hiba:gyártási év!");
                    }
                });

    }
}


class NetworkDevice2 {
    String name;
    String ip;
    int ports;
    boolean active = false;

    public NetworkDevice2(String name, String ip, int ports) {
        this.name = name;
        this.ip = ip;
        this.ports = ports;
    }

    public void powerOn() {
        this.active = true;
    }

    public String getInfo() {
        return String.format("[%s] IP: %s | Ports: %d | Online: %b", name, ip, ports, active);
    }
}

class Firewall extends NetworkDevice2 {
    int rulesCount = 0;

    public Firewall(String name, String ip, int ports) {
        super(name, ip, ports);
    }

    public void addRule() {
        this.rulesCount++;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " | Rules: " + rulesCount;
    }
}
