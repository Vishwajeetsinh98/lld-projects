package exchange;

import pricing.PricingService;
import stocks.Stock;
import stocks.StockService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StockExchange {
    private StockService stockService;
    private PricingService pricingService;
    private final Map<Stock, OrderBook> stockOrderBookMap;
    private StockExchange() {
        stockOrderBookMap = new ConcurrentHashMap<>();
    }

    public void setStockService(StockService stockService) { this.stockService = stockService; }
    public StockService getStockService() { return stockService; }

    public void setPricingService(PricingService pricingService) { this.pricingService = pricingService; }
    public PricingService getPricingService() { return pricingService; }

    public void createStockOrderBook(Stock stock) {
        if (stockOrderBookMap.containsKey(stock)) return;
        System.out.println("[StockExchange] Created new stock order book for: " + stock.getTicker());
        stockOrderBookMap.put(stock, new OrderBook(stock));
    }

    public OrderBook getStockOrderBook(Stock stock) {
        return stockOrderBookMap.getOrDefault(stock, null);
    }

    // Singleton:
    private static final class Holder {
        private static final StockExchange INSTANCE = new StockExchange();
    }
    public static StockExchange getInstance() {
        return Holder.INSTANCE;
    }
}
