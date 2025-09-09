package posts.comments;

import interfaces.Commentable;
import privacy.PrivacyControlled;
import users.User;

import java.util.ArrayList;
import java.util.List;

public class Comment extends PrivacyControlled implements Commentable {

    private final User author;
    private final String content;
    private final List<Comment> comments;

    public Comment(PrivacyControlled parent, User author, String content) {
        super(parent, null);
        this.author = author;
        this.content = content;
        comments = new ArrayList<>();
    }

    @Override
    public Comment addComment(User commenter, String content) {
        if (!check(commenter)) throw new IllegalArgumentException("User " + commenter.getUsername() + " cannot comment on this comment");
        Comment comment = new Comment(parent, commenter, content);
        comments.add(comment);
        return comment;
    }

    @Override
    public void render(User viewer, int tabs) {
        super.render(viewer);
        System.out.println("\t".repeat(tabs) + "Author: " + author.getUsername() + "\n" + "\t".repeat(tabs) + content);
        for (Comment comment : comments) {
            comment.render(viewer, tabs + 2);;
        }
    }

    public User getAuthor() { return author; }

}
