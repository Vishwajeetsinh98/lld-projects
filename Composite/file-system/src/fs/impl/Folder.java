package fs.impl;

import fs.FileSystemItem;

import java.util.ArrayList;
import java.util.List;

public class Folder extends FileSystemItem {
    private List<FileSystemItem> items;

    public Folder(String name) {
        super(name);
        this.items = new ArrayList<>();
    }

    @Override
    public int getSize() {
        int size = 0;
        for (FileSystemItem item : items) {
            size += item.getSize();
        }
        return size;
    }

    @Override
    public void printStructure(int tabs) {
        for(int i = 0;i < tabs;i++) {
            System.out.print("\t");
        }
        System.out.println(this.name);
        for (FileSystemItem item : items) {
            item.printStructure(tabs + 4);
        }
    }

    public void addItem (FileSystemItem item) {
        items.add(item);
    }
}
