package platform.posts;

import platform.users.User;

public class UserPost extends Post {

    public UserPost(String title, String content, User author) throws Exception {
        super(title, content, author);
    }

    @Override
    public String toString() {
        return "[UserPost]\n" + super.toString();
    }
}
