package order;

import branch.table.Table;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<OrderItem> orderItems;
    private double orderAmount;
    private final Table table;

    public Order(Table table) {
        this.orderItems = new ArrayList<>();
        this.table = table;
    }

    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
        item.setOrder(this);
        orderAmount += item.getMenuItem().getPrice();
    }

    public void removeItem(OrderItem item) {
        orderItems.remove(item);
        orderAmount -= item.getMenuItem().getPrice();
    }

    public List<OrderItem> getOrderItems() { return orderItems; }

    public double getOrderAmount() { return orderAmount; }

    public Table getTable() { return table; }
}
