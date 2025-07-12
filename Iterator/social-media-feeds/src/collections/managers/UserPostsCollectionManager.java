package collections.managers;

import collections.PostsCollection;
import platform.posts.Post;
import platform.posts.UserPost;

import java.util.List;

public class UserPostsCollectionManager {
    private static UserPostsCollectionManager INSTANCE;
    private final PostsCollection<UserPost> postsCollection;
    private UserPostsCollectionManager() {
        postsCollection = new PostsCollection<>();
    }

    public static UserPostsCollectionManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UserPostsCollectionManager();
        return INSTANCE;
    }

    public void add (Post post) {
        this.postsCollection.add((UserPost) post);
    }

    public List<UserPost> getAll() {return this.postsCollection.get();}
}
