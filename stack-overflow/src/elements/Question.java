package elements;

import users.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question extends StackOverflowElement implements Closeable, Deletable {

    private final List<Answer> answers;
    private final List<Comment> comments;
    private final Set<String> tags;
    private int bounty;
    private int votesToClose;
    private int votesToDelete;

    public Question(String id, User user, String text) {
        super(id, user, text, null);
        answers = new ArrayList<>();
        comments = new ArrayList<>();
        tags = new HashSet<>();
        bounty = 0;
    }

    @Override
    public void print(int tabs) {
        super.print(tabs);
        String spacing = "\t".repeat(tabs);
        if (!answers.isEmpty()) {
            System.out.println(spacing + " Answers:");
            for (Answer answer : answers)
                answer.print(tabs + 2);
        }
        if (!comments.isEmpty()) {
            System.out.println(spacing + " Comments:");
            for (Comment comment : comments)
                comment.print(tabs + 2);
        }
        System.out.println("==========================================================");
    }

    public void addAnswer(Answer answer) {
        if (answers.contains(answer)) return;
        System.out.println("[Question] " + id + " added new answer " + answer.getId());
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void addComment(Comment comment) {
        if (comments.contains(comment)) return;
        System.out.println("[Question] " + id + " added new comment " + comment.getId());
        comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addTag(String tag) {
        if (tags.contains(tag)) return;
        System.out.println("[Question] " + id + " added new tag " + tag);
        tags.add(tag);
    }

    public Set<String> getTags() {
        return tags;
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    public int getBounty() {
        return bounty;
    }

    public void setBounty(int bounty) {
        System.out.println("[Question] " + id + ", setting bounty to " + bounty);
        this.bounty = bounty;
    }

    @Override
    public void voteToClose() {
        System.out.println("[Question] " + id + " new vote to close!");
        votesToClose++;
    }

    @Override
    public void close() {
        System.out.println("[Question] " + id + " closed!");
        isClosed = true;
    }

    @Override
    public int getVotesToClose() {
        return votesToClose;
    }

    @Override
    public void voteToDelete() {
        System.out.println("[Question] " + id + " new vote to delete!");
        votesToDelete++;
    }

    @Override
    public void delete() {
        System.out.println("[Question] " + id + " deleted!");
        isDeleted = true;
    }

    @Override
    public int getVotesToDelete() {
        return votesToDelete;
    }

    @Override
    public void upvote() {
        System.out.println("[Question] " + id + " upvoted.");
        super.upvote();
    }

    @Override
    public void downvote() {
        System.out.println("[Question] " + id + " downvoted.");
        super.downvote();
    }
}
