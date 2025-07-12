package iterators;

import collections.managers.AdPostsCollectionManager;
import java.util.ArrayList;

public class AdPostsIterator extends FeedIterator {
    public AdPostsIterator() {
        super();
        getAndFilterPosts();
    }

    @Override
    public void reset() {
        System.out.println("Refreshing Ad posts");
        super.reset();
    }

    @Override
    protected void getAndFilterPosts() {
        posts = new ArrayList<>(AdPostsCollectionManager.getInstance().getAll());
    }
}
