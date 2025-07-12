package platform.users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User extends Creator {
    private final String email;
    public User(String name, String email) {
        super(name);
        this.email = email;
    }

    public String getEmail() {return this.email;}
    @Override
    public String toString() {
        return "[User]"+
                "\nName: " + name +
                "\nEmail: " + email +
                "\nNumFollowers: " + followers.size() +
                "\n===========================================================";
    }
}
