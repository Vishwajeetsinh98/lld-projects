package collections.managers;


import collections.creators.UserCollection;
import platform.users.User;

import java.util.List;

public class UserCollectionManager {
    private static UserCollectionManager INSTANCE;
    private final UserCollection userCollection;
    private UserCollectionManager() {
        userCollection = new UserCollection();
    }

    public static UserCollectionManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UserCollectionManager();
        return INSTANCE;
    }

    public void add (User user) {
        this.userCollection.add(user);
    }

    public User get(String name) {
        return this.userCollection.get(name);
    }

    public boolean contains(String name) {return this.userCollection.containsKey(name);}
    public List<User> getAll() {return this.userCollection.getAll();}

}