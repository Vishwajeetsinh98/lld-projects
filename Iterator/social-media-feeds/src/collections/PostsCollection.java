package collections;

import iterators.AdPostsIterator;
import iterators.FeedIterator;
import iterators.FriendPostsIterator;
import iterators.PagePostsIterator;
import platform.posts.AdPost;
import platform.posts.PagePost;
import platform.posts.Post;
import platform.posts.UserPost;

import java.util.ArrayList;

public class PostsCollection<T extends Post> {
    private final ArrayList<T> posts;

    public PostsCollection () {
        posts = new ArrayList<T>();
    }

    public void add(T post) {
        posts.add(post);
    }
    public ArrayList<T> get() {
        return posts;
    }

    public static FeedIterator createIterator(Class type, String user) {
        if (type.equals(UserPost.class)) {
            return new FriendPostsIterator(user);
        } else if (type.equals(PagePost.class)) {
            return new PagePostsIterator(user);
        } else if (type.equals(AdPost.class)) {
            return new AdPostsIterator();
        }
        return new AdPostsIterator();
    }
}
