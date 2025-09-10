package orders.impl;

import orders.Order;
import orders.OrderDirection;
import orders.OrderType;
import stocks.Stock;
import users.User;

public class MarketOrder extends Order {
    public MarketOrder(Stock stock, User trader, OrderType orderType, OrderDirection orderDirection, int quantity) {
        super(stock, trader, orderType, orderDirection, quantity);
    }
}
