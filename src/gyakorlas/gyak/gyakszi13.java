package gyakorlas.gyak;

import java.util.*;
import java.util.stream.*;

public class gyakszi13 {
    static void main(String[] args) {

        List<String> rawLogs = Arrays.asList(
                "1|Light-Bulb|60",
                "2|Gaming-PC|500",
                "3|Router|15",
                "4|Toaster|1200",
                "5|Fridge|invalid",  // HIBÁS ADAT
                "6|AC-Unit|2500",
                "7|Unknown|0"
        );

        String jsonLog = rawLogs.stream()
                .map(line -> {
                    String[] parts = line.split("\\|");
                    try {
                        int watt = Integer.parseInt(parts[2]);
                        String category = (watt > 1000) ? "HIGH" : "NORMAL";
                        // Ez a formázás segít, hogy ne zavarodj bele az idézőjelekbe
                        return String.format("{\"device\": \"%s\", \"load\": \"%s\"}", parts[1], category);
                    } catch (Exception e) {
                        return null; // A hibásat jelöljük null-al
                    }
                })
                .filter(Objects::nonNull) // A null-okat (hibásakat) itt dobjuk ki
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(jsonLog);

        List<String> ports = Arrays.asList("Port1", "Port2", "Port3");

        String report = ports.stream()
                .map(p -> String.format("Active: %s", p))
                .collect(Collectors.joining(" | ", "<", ">"));

        System.out.println(report);

    }
}
