package orders.impl;

import exchange.StockExchange;
import orders.Order;
import orders.OrderDirection;
import orders.OrderType;
import pricing.PriceChangeListener;
import stocks.Stock;
import users.User;

public class StopLimitOrder extends Order implements PriceChangeListener {
    public StopLimitOrder(Stock stock, User trader, OrderType orderType, OrderDirection orderDirection, int quantity, double price) {
        super(stock, trader, orderType, orderDirection, quantity, price);
        StockExchange.getInstance().getPricingService().addStockListener(stock, this);
    }


    @Override
    public void getPriceChangeNotification(Stock stock) {
        System.out.println("[StopLimitOrder] Received a price change on the stock: " + stock.getTicker() + " new price: " + stock.getPrice());

        double stockPrice = stock.getPrice();
        boolean triggered = false;

        if (stockPrice <= price && orderDirection == OrderDirection.SHORT) {
            System.out.println("[StopLimitOrder] Sell condition triggered since price went below target of " + price);
            triggered = true;
        }

        if (stockPrice >= price && orderDirection == OrderDirection.LONG) {
            System.out.println("[StopLimitOrder] Buy condition triggered since price went above target of " + price);
            triggered = true;
        }

        if (triggered) {
            System.out.println("[StopLimitOrder] Converting this order into a limit order and adding to order book.");
            orderType = OrderType.LIMIT;
            StockExchange.getInstance().getStockOrderBook(stock).addOrder(this);
            // If converted - remove ourselves from listeners list
            StockExchange.getInstance().getPricingService().removeStockListener(stock, this);
        } else {
            System.out.println("[StopLimitOrder] No trigger - ignoring this price updated.");
        }
    }
}
