package iterators;

import platform.posts.Post;

import java.util.List;

public abstract class FeedIterator {

    protected int index;
    protected List<Post> posts;
    FeedIterator() {
        this.index = 0;
        getAndFilterPosts();
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean hasNext() {
        return this.index != posts.size();
    }
    public Post getNext() {
        if (!hasNext()) return null;
        return posts.get(index++);
    }
    public void reset() {
        this.index = 0;
        getAndFilterPosts();
    }
    protected abstract void getAndFilterPosts();
}
