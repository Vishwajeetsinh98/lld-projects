package order;

import branch.menu.MenuItem;

public class OrderItem {
    private final MenuItem menuItem;
    private OrderItemState orderItemState;
    private Order order;
    public OrderItem(Order order, MenuItem menuItem) {
        this.order = order;
        this.menuItem = menuItem;
        orderItemState = OrderItemState.RECEIVED;
    }

    public MenuItem getMenuItem() { return menuItem; }

    public OrderItemState getOrderItemState() { return orderItemState; }

    public void setOrderItemState(OrderItemState orderItemState) { this.orderItemState = orderItemState; }

    public Order getOrder() { return order; }

    public void setOrder(Order order) { this.order = order; }
}
