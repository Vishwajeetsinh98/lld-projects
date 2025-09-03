package elements;

import users.User;

public class Comment extends StackOverflowElement implements Deletable {

    private int votesToDelete;

    public Comment(String id, User user, String text, StackOverflowElement parent) {
        super(id, user, text, parent);
        votesToDelete = 0;
    }

    @Override
    public void upvote() {
        System.out.println("[Comment] " + id + " upvoted.");
        super.upvote();
    }

    @Override
    public void downvote() {
        System.out.println("[Comment] " + id + " downvoted.");
        super.downvote();
    }

    @Override
    public void voteToDelete() {
        System.out.println("[Comment] " + id + " new vote to delete!");
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
