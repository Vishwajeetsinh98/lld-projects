package library;

public class LibraryCard {

    private static long autoIncID = 123456L;

    private final long id;
    public LibraryCard() {
        this.id = getNextId();
    }

    public long getId() {return this.id;}

    private static long getNextId() {
        return autoIncID++;
    }

    public String toString() {
        return Long.toString(id);
    }

}
