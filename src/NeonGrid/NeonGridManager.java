package NeonGrid;

import NeonGrid.Entities.DevSyndicate;
import NeonGrid.Entities.Entity;
import NeonGrid.Entities.EntityRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NeonGridManager {
    private List<Entity> population = new ArrayList<>();
    private int cycleCounter = 0;
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean isRunning = true;
        String command;

        while (isRunning) {

            command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "status":
                    status();
                    cycleCounter++;
                    break;
                case "reboot":
                    reboot();
                    clearPopulation();
                    cycleCounter++;
                    break;
                case "quit":
                    System.out.println("Total population: " + getAllPopulationSize());
                    System.out.println("Quitting...");
                    System.out.println("Have a nice day!");
                    cycleCounter = 0;
                    isRunning = false;
                    break;
                default:
                    isPayTime();
                    Entity entity = EntityRegister.registerEntity(command);
                    if (entity != null) {
                        population.add(entity);
                    }
                    cycleCounter++;
            }
        }


    }

    private void isPayTime() {
        if (cycleCounter % 3 == 0) {
            population.forEach(Entity::addIncome);
        }
    }

    private void status() {
        double energyCost = population.stream()
                .mapToDouble(Entity::calculateTotalCost)
                .sum();
        System.out.println("\nTotal energy consumption: " + energyCost + " GIGAWATT");
    }

    private void reboot() {
        population.forEach(Entity::isBroke);
    }

    private void clearPopulation() {
        List<Entity> tempList = population.stream()
                .filter(Entity::canPayBills)
                .toList();

        population.removeAll(tempList);
    }

    private long getAllPopulationSize() {
        long couples = population.stream()
                .filter(p -> p instanceof DevSyndicate)
                .count();
        return population.size() + couples;
    }

}
