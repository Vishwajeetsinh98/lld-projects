package iterators;

import collections.managers.UserCollectionManager;
import collections.managers.UserPostsCollectionManager;
import platform.users.User;

import java.util.ArrayList;

public class FriendPostsIterator extends FeedIterator {

    // User whose friends' posts we want to see
    private final String user;

    public FriendPostsIterator(String user) {
        super();
        this.user = user;
    }

    @Override
    public void reset() {
        System.out.println("Refreshing friend posts list");
        super.reset();
    }

    @Override
    protected void getAndFilterPosts() {
        posts = new ArrayList<>(UserPostsCollectionManager.getInstance().getAll());
        posts.removeIf(post -> {
            User author = UserCollectionManager.getInstance().get(post.getAuthorName());
            return !author.hasFollower(user);
        });
    }
}
