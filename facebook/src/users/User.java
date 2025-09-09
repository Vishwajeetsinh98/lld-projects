package users;

import groups.Group;
import interfaces.Followable;
import interfaces.PrivacyController;
import posts.Post;
import posts.comments.Comment;
import privacy.PrivacyControlled;
import privacy.PrivacyType;
import system.FacebookSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements Followable, PrivacyController {
    private final String username;
    private final Profile profile;
    private final Set<User> friends;
    private final Set<Followable> following;
    private final Set<User> followers;
    private final Set<User> blocked;
    private final List<User> requests;
    private final FacebookSystem facebookSystem;

    public User(String username) {
        this.username = username;
        profile = new Profile(this);
        friends = new HashSet<>();
        following = new HashSet<>();
        followers = new HashSet<>();
        blocked = new HashSet<>();
        requests = new ArrayList<>();
        facebookSystem = FacebookSystem.getInstance();
    }

    public Post createPost(String content) {
        System.out.println("[User] " + username + " creating a new post.");
        return facebookSystem.createPost(this, content);
    }

    public Comment addComment(Post post, String content) {
        System.out.println("[User] " + username + " commenting on a post.");
        return facebookSystem.createComment(post, this, content);
    }

    public Comment addComment(Comment comment, String content) {
        System.out.println("[User] " + username + " commenting on a comment.");
        return facebookSystem.createComment(comment, this, content);
    }

    public void createFriendRequest(User to) {
        System.out.println("[User] " + username + " sending friend request to: " + to.username);
        facebookSystem.createFriendRequest(this, to);
    }

    public void addFriendRequest(User from) {
        System.out.println("[User] " + username + " received a friend request from: " + from.username);
        requests.add(from);
    }

    public void acceptRequest(User from) {
        if (!requests.contains(from)) {
            return;
        }
        facebookSystem.acceptRequest(this, from);
        requests.remove(from);
    }

    public void rejectRequest(User from) {
        if (!requests.contains(from)) return;
        facebookSystem.rejectRequest(this, from);
        requests.remove(from);
    }

    public void createGroupFollowRequest(Group to) {
        System.out.println("[User] " + username + " sending follow request to group: " + to.getName());
        facebookSystem.createGroupFollowRequest(this, to);
    }

    public void getNotification(String message) {
        System.out.println("[User] " + username + " got new notification: " + message);
    }

    @Override
    public boolean checkHasPermissions(PrivacyControlled subject, User viewer) {
        if (subject.getPrivacyType() == PrivacyType.PUBLIC) return true;
        if (viewer == this) {
            System.out.println("[User] " + username + " allowed to see self content");
            return true;
        }
        if (blocked.contains(viewer)) {
            System.out.println("[User] " + username + " not allowing blocked user " + viewer.getUsername() + " to view content.");
            return false;
        }
        if (friends.contains(viewer) || followers.contains(viewer)) {
            System.out.println("[User] " + username + " allowing friend/follower " + viewer.getUsername() + " to view content.");
            return true;
        }
        System.out.println("[User] " + username + " not allowing user: " + viewer.getUsername() + " to view content.");
        return false;
    }

    public void addFriend(User friend) {
        if (friends.add(friend))
            System.out.println("[User] " + username + " added new friend: " + friend.username);
    }

    public void removeFriend(User friend) {
        if (!friends.contains(friend)) return;
        friends.remove(friend);
        System.out.println("[User] " + username + " removed friend: " + friend.username);
    }

    @Override
    public void addFollower(User follower) {
        if (followers.add(follower))
            System.out.println("[User] " + username + " added new follower: " + follower.username);
    }

    @Override
    public void removeFollower(User follower) {
        if (!followers.contains(follower)) return;
        followers.remove(follower);
        System.out.println("[User] " + username + " removed follower: " + follower.username);
    }

    public void addFollowing(Followable followable) {
        if (following.contains(followable)) return;
        following.add(followable);
        if (followable instanceof User)
            System.out.println("[User] " + username + " following new user: " + ((User)followable).username);
        else
            System.out.println("[User] " + username + " following new group: " + ((Group)followable).getName());
    }

    public void removeFollowing(Followable followable) {
        if (!following.contains(followable)) return;
        following.remove(followable);
        if (followable instanceof User)
            System.out.println("[User] " + username + " no longer following user: " + ((User)followable).username);
        else
            System.out.println("[User] " + username + " no longer following group: " + ((Group)followable).getName());
    }

    public void blockUser(User user) {
        if (blocked.add(user))
            System.out.println("[User] " + username + " blocked user: " + user.getUsername());
    }

    public void unblockUser(User user) {
        if (!blocked.contains(user)) return;
        blocked.remove(user);
        System.out.println("[User] " + username + " unblocked user: " + user.getUsername());
    }

    public void updateProfileField(String field, String value) {
        switch (field) {
            case "name":
                profile.setName(value);
                break;
            case "currentAddress":
                profile.setCurrentAddress(value);
                break;
            case "email":
                profile.setEmail(value);
                break;
            case "phone":
                profile.setPhone(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid profile field");
        }
    }

    public void addWorkExperience(String company, String title, LocalDate start, LocalDate end) {
        profile.addWorkExperience(company, title, start, end);
        System.out.println("[User] " + username + " added a new work-ex");
    }

    public void addEducation(String school, String program, LocalDate start, LocalDate end) {
        profile.addEducation(school, program, start, end);
        System.out.println("[User] " + username + " added a new education");
    }

    public void addAddress(String city, LocalDate start, LocalDate end) {
        profile.addAddress(city, start, end);
        System.out.println("[User] " + username + " added a new past-address");
    }

    public void showOtherUserProfile(User otherUser) {
        System.out.println("[User] " + username + " trying to see profile for: " + otherUser.username);
        otherUser.getProfile().render(this);
    }

    public String getUsername() { return username; }

    public Profile getProfile() { return profile; }

    public Set<User> getFriends() { return friends; }

    public Set<Followable> getFollowing() { return following; }

    @Override
    public Set<User> getFollowers() { return followers; }

    public Set<User> getBlocked() { return blocked; }

    public List<User> getUsersToNotify() {
        Set<User> ret = new HashSet<>();
        ret.addAll(friends);
        ret.addAll(followers);
        return ret.stream().filter(e -> !blocked.contains(e)).toList();
    }
}
