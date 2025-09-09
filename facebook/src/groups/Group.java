package groups;

import interfaces.Followable;
import system.FacebookSystem;
import users.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group implements Followable {
    private final String name;
    private final Set<User> followers;
    private final List<User> requests;
    private final FacebookSystem facebookSystem;
    private User admin;
    public Group(String name) {
        this.name = name;
        followers = new HashSet<>();
        requests = new ArrayList<>();
        facebookSystem = FacebookSystem.getInstance();
    }

    public Group (String name, User admin) {
        this(name);
        setAdmin(admin);
    }

    public void addFollowRequest(User from) {
        System.out.println("[Group] " + name + " received a follow request from: " + from.getUsername());
        requests.add(from);
    }

    public void acceptRequest(User caller, User from) {
        if (caller != admin)
            throw new IllegalArgumentException("Group follow requests can only be accepted by admins!");
        if (!requests.contains(from)) {
            return;
        }
        facebookSystem.acceptGroupRequest(this, from);
        requests.remove(from);
    }

    public void rejectRequest(User caller, User from) {
        if (caller != admin)
            throw new IllegalArgumentException("Group follow requests can only be accepted by admins!");
        if (!requests.contains(from)) return;
        facebookSystem.rejectGroupRequest(this, from);
        requests.remove(from);
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
