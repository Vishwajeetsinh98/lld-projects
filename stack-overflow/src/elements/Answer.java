package elements;

import users.User;

import java.util.ArrayList;
import java.util.List;

public class Answer extends StackOverflowElement implements Deletable {

    private final List<Comment> comments;
    private int votesToDelete;

    public Answer(String id, User user, String text, StackOverflowElement parent) {
        super(id, user, text, parent);
        comments = new ArrayList<>();
        votesToDelete = 0;
    }

    @Override
    public void print(int tabs) {
        super.print(tabs);
        String spacing = "\t".repeat(tabs);
        if (!comments.isEmpty()) {
            System.out.println(spacing + " Comments:");
            for (Comment comment : comments)
                comment.print(tabs + 2);
        }
        System.out.println("----------------------------------------------------------");
    }

    @Override
    public void upvote() {
        System.out.println("[Answer] " + id + " upvoted.");
        super.upvote();
    }

    @Override
    public void downvote() {
        System.out.println("[Answer] " + id + " downvoted.");
        super.downvote();
    }

    public void addComment(Comment comment) {
        if (comments.contains(comment)) return;
        System.out.println("[Answer] " + id + " added new comment " + comment.getId());
        comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void voteToDelete() {
        System.out.println("[Answer] " + id + " new vote to delete!");
        votesToDelete++;
    }

    @Override
    public void delete() {
        System.out.println("[Answer] " + id + " deleted!");
        isDeleted = true;
    }

    @Override
    public int getVotesToDelete() {
        return votesToDelete;
    }
}
