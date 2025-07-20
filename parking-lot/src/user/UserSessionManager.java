package user;

import enums.UserType;

import java.util.*;

public class UserSessionManager {

    private final Set<String> activeSessions;
    private final Map<String, User> allUsers;
    private final Set<String> userNames;

    private UserSessionManager() {
        activeSessions = new HashSet<>();
        allUsers = new HashMap<>();
        userNames = new HashSet<>();
    }

    public void register(String userName, String password, UserType userType) {
        if (userNames.contains(userName))
            throw new RuntimeException("User ID already exists!");
        User newUser = switch (userType) {
            case ADMIN -> new Admin(userName, password, userType);
            case AGENT -> new Agent(userName, password, userType);
        };
        allUsers.put(userName, newUser);
        userNames.add(userName);
    }

    public void login(String userName, String password) {
        if (activeSessions.contains(userName)) {
            System.out.println("User already logged in");
            return;
        }
        if (allUsers.get(userName).login(password)) {
            activeSessions.add(userName);
        } else {
            throw new RuntimeException("Login failed");
        }
    }

    public void logout(String userName) {
        if (activeSessions.contains(userName)) {
            activeSessions.remove(userName);
        } else {
            System.out.println("User not logged in");
        }
    }


    private static class Holder {
        private static final UserSessionManager INSTANCE = new UserSessionManager();
    }
    public UserSessionManager getInstance() {
        return Holder.INSTANCE;
    }

}
