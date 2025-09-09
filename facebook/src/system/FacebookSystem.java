package system;

import NotificationSystem.NotificationSystem;
import groups.Group;
import posts.Post;
import posts.comments.Comment;
import privacy.PrivacyControlled;
import users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FacebookSystem {
    private final Map<User, List<Post>> userPostMap;
    private final Map<Group, List<Post>> groupPostMap;
    private final Map<String, User> userIdMap;
    private final Map<String, Group> groupIdMap;

    private FacebookSystem() {
        userPostMap = new ConcurrentHashMap<>();
        groupPostMap = new ConcurrentHashMap<>();
        userIdMap = new ConcurrentHashMap<>();
        groupIdMap = new ConcurrentHashMap<>();
    }

    public Post createPost(User author, String content) {
        Post newPost = new Post(author, content);
        if (!userPostMap.containsKey(author))
            userPostMap.put(author, new ArrayList<>());
        userPostMap.get(author).add(newPost);

        for (User user : author.getUsersToNotify()) {
            NotificationSystem.notifyUser(user, "New post from: " + author);
        }
        return newPost;
    }

    public Comment createComment(Post parent, User author, String content) {
        Comment comment = parent.addComment(author, content);
        NotificationSystem.notifyUser(parent.getAuthor(), "New comment on your post by " + author.getUsername());
        return comment;
    }

    public Comment createComment(Comment parent, User author, String content) {
        Comment comment = parent.addComment(author, content);
        PrivacyControlled currentParent = parent;
        PrivacyControlled lastNonNullParent = null;
        while (currentParent != null) {
            lastNonNullParent = currentParent;
            currentParent = currentParent.getParent();
        }
        User originalPostAuthor = ((Post) lastNonNullParent).getAuthor();
        NotificationSystem.notifyUser(parent.getAuthor(), "User " + author.getUsername() + " replied to your comment on " + originalPostAuthor.getUsername() + "'s post.");
        NotificationSystem.notifyUser(originalPostAuthor, "User " + author.getUsername() + " commented on your post");
        return comment;
    }

    public void createFriendRequest(User requester, User receiver) {
        receiver.addFriendRequest(requester);
        NotificationSystem.notifyUser(receiver, requester.getUsername() + " has sent you a friend request.");
    }

    public void acceptRequest(User receiver, User requester) {
        receiver.addFriend(requester);
        requester.addFriend(receiver);
        // Notify requester
        NotificationSystem.notifyUser(requester, receiver.getUsername() + " has accepted your friend request.");
    }

    public void rejectRequest(User receiver, User requester) {
        // Notify requester
        NotificationSystem.notifyUser(requester, receiver.getUsername() + " has rejected your friend request.");
    }

    public void createGroupFollowRequest(User requester, Group receiver) {
        receiver.addFollowRequest(requester);
        NotificationSystem.notifyUser(receiver.getAdmin(), requester.getUsername() + " has applied to join your group " + receiver.getName());
    }

    public void acceptGroupRequest(Group receiver, User requester) {
        receiver.addFollower(requester);
        requester.addFollowing(receiver);
        // Notify requester
        NotificationSystem.notifyUser(requester, "Your request to join group " + receiver.getName() + " has been approved.");
    }

    public void rejectGroupRequest(Group receiver, User requester) {
        // Notify requester
        NotificationSystem.notifyUser(requester, "Your request to join group " + receiver.getName() + " has been rejected.");
    }

    public void addUser(User user) {
        userIdMap.put(user.getUsername(), user);
    }

    public void addGroup(Group group) {
        groupIdMap.put(group.getName(), group);
    }

    public User getUserById(String id) {
        return userIdMap.getOrDefault(id, null);
    }

    public Group getGroupById(String id) {
        return groupIdMap.getOrDefault(id, null);
    }

    // Singleton:
    private static final class Holder {
        private static final FacebookSystem INSTANCE = new FacebookSystem();
    }
    public static FacebookSystem getInstance() {
        return Holder.INSTANCE;
    }
}
