package iterators;

import collections.managers.PageCollectionManager;
import collections.managers.PagePostsCollectionManager;
import platform.users.Page;

import java.util.ArrayList;

public class PagePostsIterator extends FeedIterator {

    // User for whose followed pages we want to find posts.
    private final String user;

    public PagePostsIterator(String user){
        super();
        this.user = user;
    }

    @Override
    public void reset() {
        System.out.println("Refreshing Posts collection");
        super.reset();
    }

    @Override
    protected void getAndFilterPosts() {
        posts = new ArrayList<>(PagePostsCollectionManager.getInstance().getAll());
        posts.removeIf(post -> {
            Page author = PageCollectionManager.getInstance().get(post.getAuthorName());
            return !author.hasFollower(user);
        });
    }
}
