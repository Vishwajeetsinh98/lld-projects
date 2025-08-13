package system.notification;

import users.User;

public class NotificationService {

    private NotificationStrategy notificationStrategy;
    private NotificationService() {
        this.notificationStrategy = new PushNotificationStrategy();
    }

    public void changeNotificationStrategy(NotificationStrategy strategy) {
        this.notificationStrategy = strategy;
    }

    public void sendMessage(User user, String message) {
        this.notificationStrategy.send(user, message);
    }

    private static class Holder {
        private static final NotificationService INSTANCE = new NotificationService();
    }

    public static NotificationService getInstance() {
        return Holder.INSTANCE;
    }
}
