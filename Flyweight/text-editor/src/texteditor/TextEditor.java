package texteditor;

import character.Character;

import java.util.ArrayList;
import java.util.List;

public class TextEditor {
    private String fileName;
    private final List<Character> characters;

    public TextEditor () {
        this.fileName = "untitled";
        characters = new ArrayList<>();
    }

    public TextEditor (String fileName) {
        this.fileName = fileName;
        characters = new ArrayList<>();
    }

    public void addCharacter (char ch, int x, int y, String fontName, int fontSize, String fontColor) {
        characters.add(new Character(ch, x, y, fontName, fontSize, fontColor));
    }

    public void backSpace() {
        if (characters.isEmpty()) return;
        characters.removeLast();
    }

    public void renameFile(String fileName) {
        this.fileName = fileName;
    }

    public void renderDocument() {
        System.out.println("==================================================");
        System.out.println(fileName);

        for (Character character : characters) {
            character.renderCharacter();
        }

        System.out.println("==================================================");
    }
}
