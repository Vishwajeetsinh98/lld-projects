package NotificationSystem;

import users.User;

public class NotificationSystem {
    public static void notifyUser(User user, String message) {
        user.getNotification(message);
    }
}
