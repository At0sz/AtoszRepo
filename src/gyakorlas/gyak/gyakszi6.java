package gyakorlas.gyak;

import java.util.*;
import java.util.stream.Collectors;

public class gyakszi6 {
    static void main(String[] args) {

        List<String> deviceLogs = Arrays.asList(
                "Cisco;192.168.1.1;1500",
                "HP;10.0.0.55;2300",
                "Cisco;192.168.1.10;1200",
                "MikroTik;10.0.0.1;500",
                "HP;10.0.0.56;4500",
                "Cisco;192.168.1.1;800", // ugyanaz az IP, új forgalom
                "CheckPoint;172.16.0.1;3000"
        );

        Map<String, List<DeviceStat>> assignedDevices = deviceLogs.stream()
                .filter(p -> p.contains(";"))
                .map(p -> {
                    String[] split = p.split(";");
                    int dataMD = Integer.parseInt(split[2]);
                    return new DeviceStat(split[0], split[1], dataMD);
                })
                .collect(Collectors.groupingBy(line -> line.manufacturer));

        assignedDevices.forEach((vendor, devices) -> {
            int total = devices.stream().mapToInt(d -> d.dataMB).sum();
            if (total >= 3000) {
                System.out.println(vendor + " összesített forgalma: " + total + " MB");
            }
        });

        assignedDevices.forEach((vendor, devices) -> {
            // 1. Kiszámoljuk az összesített forgalmat az adott gyártóhoz
            int total = devices.stream().mapToInt(d -> d.dataMB).sum();

            // 2. Csak akkor írunk ki bármit, ha eléri a 3000 MB-ot
            if (total >= 3000) {
                System.out.println("Gyártó: " + vendor);

                // 3. Belső ciklus: végigmegyünk a gyártó eszközein (a listán)
                devices.forEach(d -> System.out.println(" - " + d.ip + " (" + d.dataMB + " MB)"));

                System.out.println("Összesen: " + total + " MB\n");
            }
        });


    }
}


class DeviceStat {
    String manufacturer;
    String ip;
    int dataMB;

    DeviceStat(String manufacturer, String ip, int dataMB) {
        this.manufacturer = manufacturer;
        this.ip = ip;
        this.dataMB = dataMB;
    }

    @Override
    public String toString() {
        return "Gyártó: " + manufacturer + " IP:" + ip + " Forgalom " + dataMB + " MB";
    }


}