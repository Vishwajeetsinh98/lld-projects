package platform.posts;

import collections.managers.PageCollectionManager;
import collections.managers.UserCollectionManager;
import platform.users.Creator;

public abstract class Post {
    protected final String title;
    protected final String content;
    protected final Creator author;

    public Post(String title, String content, Creator author) throws Exception {
        this.title = title;
        this.content = content;
        if (PageCollectionManager.getInstance().contains(author.getName()) || UserCollectionManager.getInstance().contains(author.getName()))
            this.author = author;
        else
            throw new Exception ("No user/page found with the name");
    }

    public String toString() {
        return "Author: " + author.getName() +
                "\nTitle: " + title +
                "\nContent: " + content +
                "\n===================================================================";
    }

    public String getAuthorName() {return author.getName();}

}
