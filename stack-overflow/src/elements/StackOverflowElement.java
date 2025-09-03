package elements;

import users.User;

import java.util.Objects;

public abstract class StackOverflowElement {

    protected final String id;
    protected final User user;
    protected final String text;
    protected boolean isClosed;
    protected boolean isDeleted;
    protected int upvotes;
    protected int downvotes;
    protected final StackOverflowElement parent;

    public StackOverflowElement(String id, User user,
                                String text, StackOverflowElement parent) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.parent = parent;
        isClosed = false;
        upvotes = 0;
        downvotes = 0;
    }

    public void print(int tabs) {
        String spacing = "\t".repeat(tabs);
        System.out.println(spacing + id);
        System.out.println(spacing + user);
        System.out.println(spacing + text);
        System.out.println(spacing + "Upvotes: " + upvotes + " Downvotes: " + downvotes);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof StackOverflowElement)) return false;
        return ((StackOverflowElement) o).id.equals(id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    public void upvote() {
        upvotes++;
    }

    public void downvote() { downvotes++; }

    public String getId() {return id;}

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public boolean isDeleted() {return isDeleted;}

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public StackOverflowElement getParent() {return parent;}
}
