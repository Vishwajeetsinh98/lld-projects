package interfaces;

import users.User;

import java.util.Set;

public interface Followable {
    void addFollower(User follower);
    void removeFollower(User follower);
    Set<User> getFollowers();
}
