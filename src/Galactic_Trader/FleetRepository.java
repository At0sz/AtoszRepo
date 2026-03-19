package Galactic_Trader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FleetRepository {


    private Map<String, SpaceShip> shipMap = new HashMap<>();

    public void addShip(SpaceShip ship) {
        shipMap.put(ship.getModel(), ship);
    }

    public SpaceShip findShipByModel(String model) {
        return shipMap.get(model);
    }

    public List<SpaceShip> getAllShips() {
        // A Map értékeit Stream-mé alakítjuk, majd listává
        return shipMap.values().stream().toList();
    }

    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Itt a shipMap értékein (values()) megyünk végig!
            for (SpaceShip ship : shipMap.values()) {
                String line = ship.getModel() + ";" +
                        ship.getCargoCapacity() + ";" +
                        ship.getCurrentFuel() + ";" +
                        ship.getPilot().getName();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Mentés sikeres! ✅");
        } catch (IOException e) {
            System.out.println("Hiba a mentés során: " + e.getMessage());
        }
    }

    public void loadFromFile(String fileName) {
        // Tisztítsuk meg a térképet betöltés előtt, ne duplikáljunk
        shipMap.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");

                String model = data[0];
                int capacity = Integer.parseInt(data[1]);
                int fuel = Integer.parseInt(data[2]);
                String pilotName = data[3];

                SpaceShip loadedShip = new SpaceShip(model, capacity);
                loadedShip.setCurrentFuel(fuel);
                loadedShip.setPilot(new Pilot(pilotName, 0));

                // A list.add helyett most már a Map-be rakjuk!
                this.addShip(loadedShip);
            }
            System.out.println("Betöltés kész! 🚀");
        } catch (FileNotFoundException e) {
            System.out.println("Még nincs mentett fájl, üres flottával indulunk.");
        } catch (IOException e) {
            System.out.println("Hiba történt a beolvasáskor!");
        }
    }
}