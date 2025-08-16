package table;

import java.util.Scanner;

public class GameInputController {
    private final Scanner scanner;

    public GameInputController() {
        scanner = new Scanner(System.in);
    }

    public char getNextInput() {
        return scanner.next().charAt(0);
    }

    public double getNextNumber() {
        return scanner.nextDouble();
    }
}
