package platform.posts;

import platform.users.Page;

public class PagePost extends Post {

    public PagePost(String title, String content, Page author) throws Exception {
        super(title, content, author);
    }

    @Override
    public String toString() {
        return "[PagePost]\n" + super.toString();
    }
}
