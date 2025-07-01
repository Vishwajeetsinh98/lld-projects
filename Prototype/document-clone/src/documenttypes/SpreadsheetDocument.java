package documenttypes;

public class SpreadsheetDocument extends Document {

    private String[] sheets;

    public SpreadsheetDocument(String fileName, String[] sheets) {
        super(fileName);
        this.sheets = sheets;
    }

    public SpreadsheetDocument(SpreadsheetDocument doc) {
        super(doc);
        this.sheets = doc.sheets != null ? doc.sheets.clone() : null;
    }

    public SpreadsheetDocument clone() {
        return new SpreadsheetDocument(this);
    }
}