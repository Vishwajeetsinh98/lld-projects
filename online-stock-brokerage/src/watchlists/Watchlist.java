package watchlists;

import exchange.StockExchange;
import pricing.PriceChangeListener;
import pricing.PricingService;
import stocks.Stock;
import users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Watchlist implements PriceChangeListener {
    private static final AtomicLong idCounter = new AtomicLong(1);

    private final String id;
    private final List<Stock> stocks;
    private final List<User> subscribers;
    private final PricingService pricingService;

    public Watchlist() {
        id = "WL_" + idCounter.getAndIncrement();
        stocks = new ArrayList<>();
        subscribers = new ArrayList<>();
        pricingService = StockExchange.getInstance().getPricingService();
    }

    public void addStock(Stock stock) {
        if (stocks.contains(stock)) return;
        System.out.println("[Watchlist] adding new stock " + stock.getTicker() + " to watchlist: " + id);
        stocks.add(stock);
        pricingService.addStockListener(stock, this);
    }

    public void removeStock(Stock stock) {
        if (!stocks.contains(stock)) return;
        System.out.println("[Watchlist] removing stock " + stock.getTicker() + " to watchlist: " + id);
        stocks.remove(stock);
        pricingService.removeStockListener(stock, this);
    }

    @Override
    public void getPriceChangeNotification(Stock stock) {
        if (!stocks.contains(stock)) {
            System.out.println("[Watchlist] " + id + " received a price change notification on a stock not in watchlist: " + stock.getTicker());
            return;
        }
        System.out.println("[Watchlist] " + id + " received a price change notification on stock " + stock.getTicker());
        for (User user : subscribers) {
            user.getWatchlistNotification(stock);
        }
    }

    public void subscribe(User user) {
        if (subscribers.contains(user)) return;
        System.out.println("[Watchlist] " + id + " new subscriber: " + user.getId());
        subscribers.add(user);
    }

    public void unsubscribe(User user) {
        System.out.println("[Watchlist] " + id + " removing subscriber: " + user.getId());
        subscribers.remove(user);
    }

    public String getId() {
        return id;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }
}
