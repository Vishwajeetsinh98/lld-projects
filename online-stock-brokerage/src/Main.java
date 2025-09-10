import exchange.OrderBook;
import exchange.StockExchange;
import orders.Order;
import orders.OrderDirection;
import orders.OrderType;
import orders.impl.MarketOrder;
import orders.impl.StopLossOrder;
import pricing.PricingService;
import stocks.Stock;
import stocks.StockService;
import users.User;
import watchlists.Watchlist;

public class Main {
    public static void main(String[] args) {

        StockService stockService = new StockService();
        StockExchange exchange = StockExchange.getInstance();
        exchange.setStockService(stockService);

        stockService.addStock("GOOG", "Google", 235.7);
        stockService.addStock("AAPL", "Apple", 239.69);
        stockService.addStock("AMZN", "Amazon", 235.84);
        stockService.addStock("TSLA", "Tesla", 344.84);

        PricingService pricingService = new PricingService();
        exchange.setPricingService(pricingService);

        Watchlist list = new Watchlist();
//        list.addStock(stockService.getStock("GOOG"));
//        list.addStock(stockService.getStock("AAPL"));
//        list.addStock(stockService.getStock("AMZN"));

        User user = new User("user");
        User user2 = new User("user2");

        list.subscribe(user);

        Stock goog = stockService.getStock("GOOG");
        OrderBook orderBook = StockExchange.getInstance().getStockOrderBook(goog);

        Order buyLossOrder = new StopLossOrder(goog, user, OrderType.STOP_LOSS, OrderDirection.LONG, 100, 240);
        pricingService.updatePrice(goog, 245);
        orderBook.print();

        Order buyOrder = new MarketOrder(goog, user, OrderType.MARKET, OrderDirection.LONG, 100);
        Order sellOrder = new MarketOrder(goog, user2, OrderType.MARKET, OrderDirection.SHORT, 100);
        Order sellOrder2 = new MarketOrder(goog, user, OrderType.MARKET, OrderDirection.SHORT, 100);
        Order buyOrder2 = new MarketOrder(goog, user2, OrderType.MARKET, OrderDirection.LONG, 100);

        orderBook.addOrder(buyOrder);
        orderBook.addOrder(sellOrder);
        orderBook.matchOrders();

        orderBook.addOrder(sellOrder2);
        orderBook.addOrder(buyOrder2);

        System.out.println(user.getPortfolio());
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        orderBook.matchOrders();
        System.out.println(user.getPortfolio());

    }
}