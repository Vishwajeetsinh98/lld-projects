package collections.managers;


import collections.creators.PageCollection;
import platform.users.Page;
import platform.users.User;

import java.util.List;

public class PageCollectionManager {
    private static PageCollectionManager INSTANCE;
    private final PageCollection pageCollection;
    private PageCollectionManager() {
        pageCollection = new PageCollection();
    }

    public static PageCollectionManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new PageCollectionManager();
        return INSTANCE;
    }

    public void add (Page page) {
        this.pageCollection.add(page);
    }

    public Page get(String name) {
        return this.pageCollection.get(name);
    }

    public boolean contains(String name) {return this.pageCollection.containsKey(name);}
    public List<Page> getAll() {return this.pageCollection.getAll();}
}
