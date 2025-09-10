package orders;

import stocks.Stock;
import users.User;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Order {
    private static final AtomicLong idCounter = new AtomicLong();

    protected final String id;
    protected User trader;
    protected final Stock stock;
    protected OrderType orderType;
    protected final OrderDirection orderDirection;
    protected final int quantity;
    protected double price;
    protected double fillPrice;
    protected final LocalDateTime orderDate;
    protected LocalDateTime fillDate;
    protected OrderStatus status;

    public Order(Stock stock, User trader, OrderType orderType, OrderDirection orderDirection, int quantity) {
        this.stock = stock;
        this.trader = trader;
        this.orderType = orderType;
        this.orderDirection = orderDirection;
        id = "O_" + orderDirection.toString().charAt(0) + "_" + idCounter.getAndIncrement();
        this.quantity = quantity;
        price = 0.0;
        fillPrice = 0.0;
        orderDate = LocalDateTime.now();
        fillDate = null;
        if (orderType != OrderType.MARKET && orderType != OrderType.LIMIT)
            status = OrderStatus.PLACED;
        else
            status = OrderStatus.ACTIVE;
        if (orderType == OrderType.MARKET)
            validate();
    }

    public Order(Stock stock, User trader, OrderType orderType,
                 OrderDirection orderDirection,
                 int quantity, double price) {
        this(stock, trader, orderType, orderDirection, quantity);
        this.price = price;
        validate();
    }

    public void validate() {
        if (orderType != OrderType.MARKET && price == 0.0)
            throw new IllegalArgumentException("Only market orders are allowed to have 0 price, or no-price");
    }

    public void fillOrder(double tradePrice) {
        System.out.println("[Order] " + id + " getting filled with price: " + tradePrice);
        fillPrice = tradePrice;
        fillDate = LocalDateTime.now();
        status = OrderStatus.FILLED;

        if (orderDirection == OrderDirection.LONG) {
            trader.getPortfolio().addLot(stock, quantity, fillPrice);
        } else {
            trader.getPortfolio().removeLot(stock, quantity, fillPrice);
        }
    }

    public Stock getStock() { return stock; }

    public OrderType getOrderType() { return orderType; }

    public OrderDirection getOrderDirection() { return orderDirection; }

    public int getQuantity() { return quantity; }

    public double getPrice() { return price; }

    public LocalDateTime getOrderDate() { return orderDate; }

    public LocalDateTime getFillDate() { return fillDate; }

    public double getFillPrice() { return fillPrice; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
