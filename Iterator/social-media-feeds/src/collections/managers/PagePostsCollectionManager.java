package collections.managers;

import collections.PostsCollection;
import platform.posts.PagePost;
import platform.posts.Post;
import platform.posts.UserPost;

import java.util.List;

public class PagePostsCollectionManager {
    private static PagePostsCollectionManager INSTANCE;
    private final PostsCollection<PagePost> postsCollection;
    private PagePostsCollectionManager() {
        postsCollection = new PostsCollection<>();
    }

    public static PagePostsCollectionManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new PagePostsCollectionManager();
        return INSTANCE;
    }

    public void add (Post post) {
        this.postsCollection.add((PagePost) post);
    }

    public List<PagePost> getAll() {return this.postsCollection.get();}
}
