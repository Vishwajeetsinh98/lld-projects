package notification;

import stocks.Stock;
import users.User;

public class NotificationService {
    public static void sendMessage(User user, String ticker, double price) {
        user.getWatchlistNotification(ticker, price);
    }
}
