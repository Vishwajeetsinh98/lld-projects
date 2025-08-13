package users;

public class Member extends User {
    private int numberOfBooksBorrowed;

    public Member(String name) {
        super(name);
    }

    public int getNumberOfBooksBorrowed() {
        return numberOfBooksBorrowed;
    }

    public void incrementNumberOfBooksBorrowed() {
        this.numberOfBooksBorrowed++;
    }

    public void decrementNumberOfBooksBorrowed() {
        this.numberOfBooksBorrowed--;
    }
}