package user;

import enums.UserType;

public class Agent extends User {

    public Agent(String userName, String password, UserType userType) {
        super(userName, password, userType);
    }
}
