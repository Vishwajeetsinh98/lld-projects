package notification;

import orders.Order;
import stocks.Stock;
import users.User;

public class NotificationService {
    public static void sendMessage(User user, String ticker, double price) {
        user.getWatchlistNotification(ticker, price);
    }

    public static void sendOrderCompletionMessage(User user, Order order) {
        user.getOrderNotification(order);
    }
}
