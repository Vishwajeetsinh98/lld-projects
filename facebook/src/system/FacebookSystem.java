package system;

import groups.Group;
import posts.Post;
import users.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FacebookSystem {
    private final Map<User, Post> userPostMap;
    private final Map<Group, Post> groupPostMap;

    public FacebookSystem() {
        this.userPostMap = new ConcurrentHashMap<>();
        this.groupPostMap = new ConcurrentHashMap<>();
    }



}
