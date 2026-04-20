package settlersOOPCheckpoint.main.java.hu.progmasters.settlers;


import settlersOOPCheckpoint.main.java.hu.progmasters.settlers.userInterface.ConsoleUserInterface;
import settlersOOPCheckpoint.main.java.hu.progmasters.settlers.userInterface.UserInterface;

public class Engine {
    private final Settlers settlers;
    private boolean isItRunning;
    private final UserInterface ui;

    public Engine() {
        settlers = new Settlers();
        ui = new ConsoleUserInterface();
        isItRunning = true;
    }

    public void run() {
        while (isItRunning) {
            proccesCommand(ui.read());
        }
    }

    private void proccesCommand(String command) {
        String[] split = command.split(" ");
        String commandName = split[0].toLowerCase();

        if (commandName.equals("status")) {
            ui.print(settlers.getStatus());
        }

        switch (commandName) {
            case "build":
                if (split.length > 1) {
                    settlers.addBuilding(BuildingFactory.createBuilding(split[1]));
                }
                break;
            case "quit":
                isItRunning = false;
                return;
            case "skip":
                break;
            case "status":
                break;
            default:
                break;
        }
        settlers.makeTurn();
    }
}
