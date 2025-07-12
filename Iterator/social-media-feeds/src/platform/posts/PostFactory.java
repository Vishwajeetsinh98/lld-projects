package platform.posts;

import collections.managers.PageCollectionManager;
import collections.managers.UserCollectionManager;

public class PostFactory {

    public static final int USER = 1;
    public static final int PAGE = 2;
    public static final int AD = 3;

    public static Post createPost(int type, String title, String content, String author) throws Exception {
        switch(type) {
            case USER -> {
                return new UserPost(title, content, UserCollectionManager.getInstance().get(author));
            }
            case PAGE -> {
                return new PagePost(title, content, PageCollectionManager.getInstance().get(author));
            }
            case AD -> {
                return new AdPost(title, content, UserCollectionManager.getInstance().get(author));
            }
        }
        return null;
    }
}
