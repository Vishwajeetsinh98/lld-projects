package platform.users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Creator {
    protected final String name;
    protected final Set<String> followers;
    public Creator(String name) {
        this.name = name;
        this.followers = new HashSet<>();
    }

    public String getName() {return this.name;}
    public List<String> getFollowers() {return new ArrayList<>(this.followers);}

    public void addFollower(String followerUsername) {this.followers.add(followerUsername);}
    public boolean hasFollower(String followerUsername) {
        return this.followers.contains(followerUsername);
    }
}
