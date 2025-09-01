package notifications;

import users.User;

public class NotificationService {
    public static void sendMessage(User to, String message) {
        System.out.println("[NotificationService] sending message to: " + to.getId() + "\n" + message);
    }
}
