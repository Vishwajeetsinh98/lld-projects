package orders.impl;

import orders.Order;
import orders.OrderDirection;
import orders.OrderType;
import stocks.Stock;
import users.User;

public class LimitOrder extends Order {
    public LimitOrder(Stock stock, User trader, OrderType orderType, OrderDirection orderDirection, int quantity, double price) {
        super(stock, trader, orderType, orderDirection, quantity, price);
    }
}
