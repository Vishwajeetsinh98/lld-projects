package system.notification;

import users.User;

public interface NotificationStrategy {
    public void send(User user, String message);
}
