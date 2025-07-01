package documenttypes;

public class TextDocument extends Document {
    private String text;

    public TextDocument (String fileName, String text) {
        super(fileName);
        this.text = text;
    }

    public TextDocument(TextDocument doc) {
        super(doc);
        this.text = doc.text;
    }

    public TextDocument clone() {
        return new TextDocument(this);
    }
}
