package posts;

import interfaces.Commentable;
import interfaces.PrivacyController;
import posts.comments.Comment;
import privacy.PrivacyControlled;
import users.User;

import java.util.ArrayList;
import java.util.List;

public class Post extends PrivacyControlled implements Commentable {
    private final List<Comment> comments;
    private final User author;
    private final String content;

    public Post(User author, String content) {
        super((PrivacyController) author);
        this.author = author;
        this.content = content;
        comments = new ArrayList<>();
    }

    @Override
    public Comment addComment(User commenter, String content) {
        if (!check(commenter)) throw new IllegalArgumentException("User " + commenter.getUsername() + " cannot comment on this post.");
        Comment comment = new Comment(this, commenter, content);
        comments.add(comment);
        return comment;
    }

    @Override
    public void render(User viewer) {
        super.render(viewer);
        System.out.println("XXXXXXXXXX Rendering Post XXXXXXXXXX");
        System.out.println("Author: " + author.getUsername());
        System.out.println("Content:\n" + content);
        System.out.println("Comments: ");
        for (Comment comment : comments)
            comment.render(viewer, 2);
    }

    public User getAuthor() { return author; }

}
