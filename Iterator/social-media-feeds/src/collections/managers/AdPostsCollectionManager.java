package collections.managers;

import collections.PostsCollection;
import platform.posts.Post;
import platform.posts.AdPost;

import java.util.List;

public class AdPostsCollectionManager {
    private static AdPostsCollectionManager INSTANCE;
    private final PostsCollection<AdPost> postsCollection;
    private AdPostsCollectionManager() {
        postsCollection = new PostsCollection<>();
    }

    public static AdPostsCollectionManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new AdPostsCollectionManager();
        return INSTANCE;
    }

    public void add (Post post) {
        this.postsCollection.add((AdPost) post);
    }

    public List<AdPost> getAll() {return this.postsCollection.get();}
}
