package user;

import enums.UserType;

public abstract class User {

    protected final String userName;
    protected final String password;
    protected final UserType userType;

    public User(String userName, String password, UserType userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    public boolean login(String password) {
        return this.password.equals(password);
    }
}
