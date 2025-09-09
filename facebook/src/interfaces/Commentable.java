package interfaces;

import posts.comments.Comment;
import users.User;

public interface Commentable {
    Comment addComment(User author, String content);
}
