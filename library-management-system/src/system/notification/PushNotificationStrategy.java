package system.notification;

import system.LibraryManagementSystem;
import users.User;

public class PushNotificationStrategy implements NotificationStrategy {

    @Override
    public void send(User user, String message) {
        user.getNotification(message);
    }
}
