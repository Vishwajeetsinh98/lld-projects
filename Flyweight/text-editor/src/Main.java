import character.FlyweightFactory;
import texteditor.TextEditor;

public class Main {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor("LargeDocument.txt");

        // sizes: 5, 15, 7; worst case size: 15 bytes
        String[] fonts = {"Arial", "Times New Roman", "Courier"};

        // sizes: 5, 4, 3; worst case size: 5 bytes
        String[] colors = {"Black", "Blue", "Red"};

        // size: 4 bytes
        int[] fontSizes = {12, 14, 16};

        // Single Character object size: 9 bytes
        // All characters total size: 9 * 100_000 = 900 KB
        // Font styles are Arial + Black + 12, Times + Blue + 14, Courier + Red + 16.
        // CharacterType objects total size: 14 + 23 + 14  = 71 bytes
        // Total memory use: 971 bytes


        // WITHOUT FLYWEIGHT:
        // Each object worst case size: 9 + 24 = 33 bytes
        // Total size without Flyweight: 3.3 MB

        int totalCharacters = 100_000;

        for (int i = 0; i < totalCharacters; i++) {
            char ch = (char) ('A' + (i % 26)); // Cycles through A-Z
            int x = i % 100;
            int y = i / 100;

            String font = fonts[i % fonts.length];
            String color = colors[i % colors.length];
            int fontSize = fontSizes[i % fontSizes.length];

            textEditor.addCharacter(ch, x, y, font, fontSize, color);
        }

        textEditor.renderDocument();
        System.out.println("Document created with " + totalCharacters + " characters.");
        System.out.println("Unique CharacterType objects (Flyweights) created: " + FlyweightFactory.getTotalCharacterTypes());
    }
}