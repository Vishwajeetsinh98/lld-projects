package screen;

public class Screen {
    public void displayMessage(String message) {
        System.out.println("[Screen] " + message);
    }

    public void displayLines(String[] lines) {
        for (String line : lines) {
            System.out.println("[Screen] " + line);
        }
    }
}
