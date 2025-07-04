package fs.impl;

import fs.FileSystemItem;

public class File extends FileSystemItem {
    private int size;

    public File(String name, int size) {
        super(name);
        this.size = size;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void printStructure(int tabs) {
        for (int i = 0;i < tabs;i++) {
            System.out.print("\t");
        }
        System.out.println(this.name);
    }
}
