import exchange.StockExchange;
import pricing.PricingService;
import stocks.StockService;
import users.User;
import watchlists.Watchlist;

public class Main {
    public static void main(String[] args) {

        StockService stockService = new StockService();

        stockService.addStock("GOOG", "Google", 235.7);
        stockService.addStock("AAPL", "Apple", 239.69);
        stockService.addStock("AMZN", "Amazon", 235.84);
        stockService.addStock("TSLA", "Tesla", 344.84);

        StockExchange exchange = StockExchange.getInstance();
        exchange.setStockService(stockService);

        PricingService pricingService = new PricingService();
        exchange.setPricingService(pricingService);

        Watchlist list = new Watchlist();
        list.addStock(stockService.getStock("GOOG"));
        list.addStock(stockService.getStock("AAPL"));
        list.addStock(stockService.getStock("AMZN"));

        User user = new User("user");

        list.subscribe(user);

    }
}