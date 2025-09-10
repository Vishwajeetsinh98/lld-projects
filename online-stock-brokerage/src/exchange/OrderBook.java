package exchange;

import orders.Order;
import orders.OrderDirection;
import orders.OrderType;
import stocks.Stock;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class OrderBook {
    private final Stock stock;
    private final PriorityQueue<Order> buyOrders;
    private final PriorityQueue<Order> sellOrders;

    private final Queue<Order> buyMarketOrders;
    private final Queue<Order> sellMarketOrders;

    public OrderBook(Stock stock) {
        this.stock = stock;
        buyOrders = new PriorityQueue<>((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
        sellOrders = new PriorityQueue<>(Comparator.comparingDouble(Order::getPrice));
        buyMarketOrders = new LinkedList<>();
        sellMarketOrders = new LinkedList<>();
    }

    public void addOrder(Order order) {
        // Also avoid duplicate orders.
        if (order.getOrderType() == OrderType.MARKET) {
            if (order.getOrderDirection() == OrderDirection.LONG) {
                if (!buyMarketOrders.contains(order))
                    buyMarketOrders.offer(order);
            } else {
                if (!sellMarketOrders.contains(order))
                    sellMarketOrders.offer(order);
            }
        } else if (order.getOrderType() == OrderType.LIMIT) {
            if (order.getOrderDirection() == OrderDirection.LONG) {
                if (!buyOrders.contains(order))
                    buyOrders.add(order);
            } else {
                if (!sellOrders.contains(order))
                    sellOrders.add(order);
            }
        } else {
            throw new IllegalArgumentException("Stop loss and stop limit orders should only be added when active.");
        }
    }

    public void matchOrders() {
        // TODO: Enforce same trader cannot be in both direction.
        // TODO: Support partial fills.
        // TODO: Support LIMIT orders.

        // Preference to market orders first:
        while(!buyMarketOrders.isEmpty() && (!sellMarketOrders.isEmpty() || !sellOrders.isEmpty())) {
            Order buyOrder = buyMarketOrders.poll();
            Order sellOrder = getBestSellOrder();
            if (sellOrder == null) break;
            if (executeTrade(buyOrder, sellOrder))
                cleanupOrders(buyOrder, sellOrder);
            else
                break;
        }

        while(!sellMarketOrders.isEmpty() && (!buyMarketOrders.isEmpty() || !buyOrders.isEmpty())) {
            Order sellOrder = sellMarketOrders.poll();
            Order buyOrder = getBestBuyOrder();
            if (buyOrder == null) break;
            if (executeTrade(buyOrder, sellOrder))
                cleanupOrders(buyOrder, sellOrder);
            else
                break;
        }

    }

    private void cleanupOrders(Order buyOrder, Order sellOrder) {
        if (buyOrder.getOrderType() == OrderType.MARKET)
            buyMarketOrders.remove(buyOrder);
        else
            buyOrders.remove(buyOrder);

        if (sellOrder.getOrderType() == OrderType.MARKET)
            sellMarketOrders.remove(sellOrder);
        else
            sellOrders.remove(sellOrder);
    }

    private boolean executeTrade(Order buyOrder, Order sellOrder) {
        int quantityToFill = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());
        if (!canPlaceTrade(buyOrder, sellOrder)) {
            System.out.println("[OrderBook] these two orders cannot be executed");
            return false;
        }
        double tradePrice = determineTradePrice(buyOrder, sellOrder);

        buyOrder.fillOrder(tradePrice);
        sellOrder.fillOrder(tradePrice);

        return true;
    }

    private boolean canPlaceTrade(Order buyOrder, Order sellOrder) {
        if (buyOrder.getOrderType() == OrderType.MARKET || sellOrder.getOrderType() == OrderType.MARKET)
            return true;
        if (buyOrder.getOrderType() != OrderType.MARKET && sellOrder.getPrice() > buyOrder.getPrice())
            return false;
        return sellOrder.getOrderType() == OrderType.MARKET || !(buyOrder.getPrice() < sellOrder.getPrice());
    }

    private double determineTradePrice(Order buyOrder, Order sellOrder) {
        if (buyOrder.getOrderType() == OrderType.MARKET && sellOrder.getOrderType() == OrderType.MARKET) {
            return stock.getPrice();
        } else if (buyOrder.getOrderType() == OrderType.MARKET) {
            return sellOrder.getPrice();
        } else if (sellOrder.getOrderType() == OrderType.MARKET) {
            return buyOrder.getPrice();
        } else {
            return sellOrder.getPrice();
        }
    }

    private Order getBestBuyOrder() {
        if (!buyMarketOrders.isEmpty()) return buyMarketOrders.poll();
        if (!buyOrders.isEmpty()) return buyOrders.poll();
        return null;
    }

    private Order getBestSellOrder() {
        if (!sellMarketOrders.isEmpty()) return sellMarketOrders.poll();
        if (!sellOrders.isEmpty()) return sellOrders.poll();
        return null;
    }

    public void print() {
        System.out.println("OrderBook status for " + stock.getTicker());
        System.out.println("Market orders: ");
        System.out.println("Buy:" + buyMarketOrders.size() + " Sell: " + sellMarketOrders.size());
        System.out.println("Limit orders: ");
        System.out.println("Buy:" + buyOrders.size() + " Sell: " + sellOrders.size());
    }
}
