package order;

import order.shipment.Shipment;
import payment.PaymentStatus;
import product.Product;
import users.Customer;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Order {

    private static final AtomicLong id = new AtomicLong(1);

    private final String orderId;
    private final Map<Product, Integer> productMap;
    private Shipment shipment;
    private final Customer customer;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private final double amount;

    public Order(Map<Product, Integer> productMap, Customer customer, double amount) {
        orderId = "O_" + id.getAndIncrement();
        this.productMap = productMap;
        this.customer = customer;
        this.orderStatus = OrderStatus.PLACED;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public Map<Product, Integer> getProductMap() {
        return productMap;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
