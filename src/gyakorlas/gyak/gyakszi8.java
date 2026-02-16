package gyakorlas.gyak;

import java.util.*;
import java.util.stream.Collectors;

public class gyakszi8 {
    public static void main(String[] args) {

        // Az engedélyezett IP címek és a hozzájuk rendelt tulajdonosok
        Map<String, String> whiteList = Map.of(
                "192.168.1.10", "Admin-PC",
                "192.168.1.12", "User-01-Laptop",
                "10.0.0.1", "Main-Gateway"
        );

        // A hálózaton észlelt forgalmi logok (IP; Forgalom_MB)
        List<String> networkTraffic = Arrays.asList(
                "192.168.1.10;500",
                "192.168.1.15;1200", // ISMERETLEN IP!
                "10.0.0.1;2500",
                "172.16.0.5;300",    // ISMERETLEN IP!
                "192.168.1.12;100"
        );

        List<Traffic> unauthorizedTraffic = networkTraffic.stream()
                .map(p -> {
                    String[] split = p.split(";");
                    return new Traffic(split[0], Integer.parseInt(split[1]));
                })
                .filter(t -> !whiteList.containsKey(t.ip)) // Ez a "lelke": ha NINCS benne a fehérlistában, mehet tovább
                .toList();

        Long sumAmount = unauthorizedTraffic.stream().mapToLong(t -> t.amount).sum();

        System.out.println("Gyanús IP címek: \n");
        unauthorizedTraffic.forEach(System.out::println);
        System.out.println("Összes adat ezekről az eszközökről: " + sumAmount);
    }
}


class Traffic {
    String ip;
    int amount;

    Traffic(String ip, int amount) {
        this.ip = ip;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "IP cím: " + ip + " adat: " + amount;
    }

}