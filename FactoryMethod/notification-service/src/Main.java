import service.NotificationService;
import service.impl.EmailNotificationService;
import service.impl.PushNotificationService;
import service.impl.SmsNotificationService;

public class Main {
    public static void main(String[] args) {
        NotificationService notificationService = new SmsNotificationService();
        notificationService.sendNotification("test sms", "123456789");
        notificationService = new EmailNotificationService();
        notificationService.sendNotification("test mail", "test@xyz.com");
        notificationService = new PushNotificationService();
        notificationService.sendNotification("test push", "device");
    }
}