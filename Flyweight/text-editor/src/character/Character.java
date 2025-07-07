package character;

public class Character {
    // Unique state:
    private final int x;
    private final int y;
    private final char ch;

    // Repeating state
    private final CharacterType flyweight;

    public Character (char ch, int x, int y, String fontName, int fontSize, String fontColor) {
        this.ch = ch;
        this.x = x;
        this.y = y;
        this.flyweight = FlyweightFactory.getCharacterType(fontName, fontSize, fontColor);
    }

    public void renderCharacter() {
        this.flyweight.renderCharacter(ch, x, y);
    }
}