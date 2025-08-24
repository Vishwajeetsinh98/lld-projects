package keyboard;

import java.util.Scanner;

public class Keyboard {
    private final Scanner scanner;
    public Keyboard() {
        scanner = new Scanner(System.in);
    }

    public long getNextLong() {
        return scanner.nextLong();
    }

    public double getNextDouble() {
        return scanner.nextDouble();
    }

    public String getNextString() {
        return scanner.next();
    }
}
