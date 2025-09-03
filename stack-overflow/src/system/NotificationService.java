package system;

import users.User;

public class NotificationService {
    public void notify(User user, String message) {
        user.getNotification(message);
    }
}
