package platform.feed;

import collections.PostsCollection;
import iterators.FeedIterator;
import platform.posts.AdPost;
import platform.posts.PagePost;
import platform.posts.Post;
import platform.posts.UserPost;

public class Feed {

    private final FeedIterator friendsIterator;
    private final FeedIterator pageIterator;
    private final FeedIterator adIterator;
    private int postNum;

    public Feed(String user) {
        friendsIterator = PostsCollection.createIterator(UserPost.class, user);
        pageIterator = PostsCollection.createIterator(PagePost.class, user);
        adIterator = PostsCollection.createIterator(AdPost.class, null);
        this.postNum = 0;
    }

    public Post getNextPost() {
        if (!friendsIterator.hasNext() && !pageIterator.hasNext() && !adIterator.hasNext()) {
            return null;
        }

        postNum++;

        if (postNum % 3 == 0) {
            if (!pageIterator.hasNext())
                pageIterator.reset();
            return pageIterator.getNext();
        }
        if (postNum % 5 == 0) {
            if (!adIterator.hasNext())
                adIterator.reset();
            return adIterator.getNext();
        }
        if (!friendsIterator.hasNext())
            friendsIterator.reset();
        return friendsIterator.getNext();
    }
}
