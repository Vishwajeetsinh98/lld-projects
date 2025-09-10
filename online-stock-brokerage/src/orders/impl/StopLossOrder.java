package orders.impl;

import exchange.StockExchange;
import orders.Order;
import orders.OrderDirection;
import orders.OrderType;
import pricing.PriceChangeListener;
import stocks.Stock;
import users.User;

public class StopLossOrder extends Order implements PriceChangeListener {
    public StopLossOrder(Stock stock, User trader, OrderType orderType, OrderDirection orderDirection, int quantity, double price) {
        super(stock, trader, orderType, orderDirection, quantity, price);
        StockExchange.getInstance().getPricingService().addStockListener(stock, this);
    }

    @Override
    public void getPriceChangeNotification(Stock stock) {
        System.out.println("[StopLossOrder] Received a price change on the stock: " + stock.getTicker() + " new price: " + stock.getPrice());
        double stockPrice = stock.getPrice();
        boolean triggered = false;

        if (stockPrice <= price && orderDirection == OrderDirection.SHORT) {
            System.out.println("[StopLossOrder] Sell condition triggered since price went below target of " + price);
            triggered = true;
        }

        if (stockPrice >= price && orderDirection == OrderDirection.LONG) {
            System.out.println("[StopLossOrder] Buy condition triggered since price went above target of " + price);
            triggered = true;
        }

        if (triggered) {
            System.out.println("[StopLossOrder] Converting this order into a market order and adding to order book.");
            orderType = OrderType.MARKET;
            price = 0;
            StockExchange.getInstance().getStockOrderBook(stock).addOrder(this);
            // If converted - remove ourselves from listeners list
            StockExchange.getInstance().getPricingService().removeStockListener(stock, this);
        } else {
            System.out.println("[StopLossOrder] No trigger - ignoring this price updated.");
        }
    }
}
