package platform.posts;

import platform.users.User;

public class AdPost extends Post {

    public AdPost(String title, String content, User author) throws Exception {
        super(title, content, author);
    }

    @Override
    public String toString() {
        return "[AdPost]\n" + super.toString();
    }
}
