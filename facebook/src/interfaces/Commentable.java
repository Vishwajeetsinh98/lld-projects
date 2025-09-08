package interfaces;

import users.User;

public interface Commentable {
    void addComment(User author, String content);
}
