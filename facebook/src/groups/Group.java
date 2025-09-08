package groups;

import interfaces.Followable;
import users.User;

import java.util.HashSet;
import java.util.Set;

public class Group implements Followable {
    private final String name;
    private final Set<User> followers;
    private User admin;
    public Group(String name) {
        this.name = name;
        followers = new HashSet<>();
    }

    public Group (String name, User admin) {
        this(name);
        setAdmin(admin);
    }

    @Override
    public void addFollower(User follower) {
        if (followers.add(follower))
            System.out.println("[Group] " + name + " added new follower: " + follower.getUsername());
    }

    @Override
    public void removeFollower(User follower) {
        if (!followers.contains(follower)) return;
        System.out.println("[Group] " + name + " removed follower: " + follower.getUsername());
    }

    public String getName() { return name; }

    @Override
    public Set<User> getFollowers() { return followers; }

    public User getAdmin() { return admin; }

    public void setAdmin(User admin) { this.admin = admin; }

}
