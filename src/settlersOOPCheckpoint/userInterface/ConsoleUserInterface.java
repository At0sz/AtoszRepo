package settlersOOPCheckpoint.userInterface;

import java.util.Scanner;

public class ConsoleUserInterface implements UserInterface {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }
}

