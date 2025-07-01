package documenttypes;

public abstract class Document implements Prototype {
    private final String fileName;

    public Document (String fileName) {
        this.fileName = fileName;
    }

    public Document (Document doc) {
        this.fileName = doc.fileName;
    }

    public abstract Document clone();
}