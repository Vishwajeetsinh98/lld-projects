package users;

import exchange.StockExchange;
import orders.Order;
import pricing.PricingService;
import stocks.Stock;

import java.util.Objects;

public class User {

    private final String id;
    private final Portfolio portfolio;

    public User(String id) {
        this.id = id;
        portfolio = new Portfolio(this);
    }

    public void getWatchlistNotification(String ticker, double price) {
        System.out.println("[User] " + id + " received price change notification on " + ticker + " new price: " + price);
    }

    public void getOrderNotification(Order order) {
        System.out.println("[User] " + id + " your order " + order.getId() + " has been filled - please check your portfolio");
    }

    public String getId() { return id; }

    public Portfolio getPortfolio() { return portfolio; }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        return ((User) o).id.equals(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
