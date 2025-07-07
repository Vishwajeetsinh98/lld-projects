package character;

public class CharacterType {
    private final String fontName;
    private final double fontSize;
    private final String fontColor;

    public CharacterType(String fontName, double fontSize, String fontColor) {
        this.fontName = fontName;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
    }

    public String getFontColor() {
        return fontColor;
    }

    public double getFontSize() {
        return fontSize;
    }

    public String getFontName() {
        return fontName;
    }

    public void renderCharacter(char ch, int x, int y) {
        System.out.println(ch + " at: (" + x + ", " + y + ")[" + fontColor + ", " + fontName + ", " + fontSize + "]");
    }
}
