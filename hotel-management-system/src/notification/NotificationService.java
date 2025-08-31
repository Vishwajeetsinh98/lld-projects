package notification;

public class NotificationService {
    public void sendNotification(String user, String message) {
        System.out.println("[NotificationService] Sending message: " + message + " to user: " + user);
    }
}
