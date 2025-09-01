package users;

public abstract class User {
    protected boolean isGuest;
    protected boolean isAdmin;
    protected final String id;
    protected final String password;
    protected boolean isBlocked;
    public User (String id, String password) {
        this.id = id;
        this.password = password;
        isGuest = true;
        isAdmin = false;
        isBlocked = false;
    }

    public User(String id, String password, boolean isGuest, boolean isAdmin) {
        this(id, password);
        this.isGuest = isGuest;
        this.isAdmin = isAdmin;
    }

    public void login(String password) {
        if (!this.password.equals(password))
            throw new IllegalArgumentException("Password incorrect");
        if (isBlocked)
            throw new IllegalArgumentException("You are blocked - cannot login.");
        System.out.println("[User] " + id + " logged in. Can place orders now.");
        isGuest = false;
    }

    public void logout() {
        if (!isGuest) return;
        isGuest = false;
        System.out.println("[User] " + id + " logged out.");
    }

    public boolean canPlaceOrders() {
        return !isGuest && !isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getId() {
        return id;
    }
}
