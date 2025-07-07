import character.FlyweightFactory;
import texteditor.TextEditor;

public class Main {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor("LargeDocument.txt");

        String[] fonts = {"Arial", "Times New Roman", "Courier"};
        String[] colors = {"Black", "Blue", "Red"};
        int[] fontSizes = {12, 14, 16};

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