package editor;

public class TextDocument {
    private StringBuilder text;

    public TextDocument () {
        this.text = new StringBuilder();
    }

    public String getText() {
        return text.toString();
    }

    public void setText(String newText) {
        text = new StringBuilder(newText);
    }

    public void addText(String newText, int pos) {
        text.insert(pos, newText);
    }

    public void deleteText(int start, int length) {
        if (start >= text.length()) return;
        text.delete(start, Math.min(start + length, text.length()));
    }
}
