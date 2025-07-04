package fs;


// First started as an interface but due to some shared code,
// converted into abstract class.

public abstract class FileSystemItem {

    protected String name;

    public FileSystemItem (String name) {
        this.name = name;
    }

    public String getName() {return this.name;}
    public void rename(String newName) {this.name = newName;}

    public abstract int getSize();
    public abstract void printStructure(int tabs);
}
