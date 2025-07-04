import fs.impl.File;
import fs.impl.Folder;

public class Main {
    public static void main(String[] args) {
        Folder folder = new Folder("/u/");
        folder.printStructure(0);
        System.out.println(folder.getSize());
        folder.addItem(new File("test", 48));
        Folder subFolder = new Folder("dir/");
        folder.addItem(subFolder);
        subFolder.addItem(new File("test1", 1024));

        folder.printStructure(0);
        System.out.println(folder.getSize());
    }
}