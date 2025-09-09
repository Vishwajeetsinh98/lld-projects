package users;

import exchange.StockExchange;
import pricing.PricingService;
import stocks.Stock;

import java.util.Objects;

public class User {

    private final String id;

    public User(String id) {
        this.id = id;
    }

    public void getWatchlistNotification(Stock stock) {
        System.out.println("[User] " + id + " received price change notification on " + stock.getTicker() + " new price: " + stock.getPrice());
    }

    public String getId() { return id; }

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
