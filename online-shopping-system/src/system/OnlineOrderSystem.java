package system;

import cart.Cart;
import notifications.NotificationService;
import order.Order;
import order.OrderStatus;
import order.shipment.Shipment;
import payment.PaymentService;
import payment.PaymentStatus;
import product.Catalog;
import users.Customer;
import users.User;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class OnlineOrderSystem {

    private final Catalog catalog;
    private final Map<String, User> userMap;
    private final PaymentService paymentService;

    private OnlineOrderSystem() {
        catalog = new Catalog();
        userMap = new ConcurrentHashMap<>();
        paymentService = new PaymentService();
    }

    public Order placeOrder(Cart cart, Customer customer, String address) {
        System.out.println("[OnlineOrderSystem] creating new order");
        Order order = new Order(new ConcurrentHashMap<>(cart.getProductMap()), customer, cart.getCartTotal());

        if (new Random().nextBoolean()) {
            System.out.println("[OnlineOrderSystem] collecting payment");
            paymentService.collect(cart.getCartTotal());
            order.setPaymentStatus(PaymentStatus.PAID);
        } else {
            System.out.println("[OnlineOrderSystem] COD order");
            order.setPaymentStatus(PaymentStatus.CASH_ON_DELIVERY);
        }

        Shipment shipment = new Shipment(address);
        order.setShipment(shipment);
        NotificationService.sendMessage(customer, "Order placed successfully!");
        return order;
    }

    public void cancelOrder(Order order) {
        System.out.println("[OnlineOrderSystem] Canceling order " + order.getOrderId());
        if (order.getOrderStatus() != OrderStatus.PLACED)
            throw new IllegalArgumentException("Can only cancel orders have been placed - not shipping or delivered");
        order.setOrderStatus(OrderStatus.CANCELED);
        order.setShipment(null);
        if (order.getPaymentStatus() == PaymentStatus.PAID)
            System.out.println("[OnlineOrderSystem] refunding " + order.getAmount());
    }

    public void addUser(User user) {
        userMap.put(user.getId(), user);
    }

    public void removeUser(User user) {
        userMap.remove(user.getId());
    }

    public Catalog getCatalog() {
        return catalog;
    }


    // Singleton:
    private static final class Holder {
        private static final OnlineOrderSystem INSTANCE = new OnlineOrderSystem();
    }
    public static OnlineOrderSystem getInstance() {
        return Holder.INSTANCE;
    }
}
