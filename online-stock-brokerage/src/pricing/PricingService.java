package pricing;

import exchange.StockExchange;
import stocks.Stock;
import stocks.StockService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PricingService {
    private final Map<Stock, List<PriceChangeListener>> stockListMap;
    private final StockService stockService;
    private final StockUpdater stockUpdater;

    public PricingService() {
        stockListMap = new ConcurrentHashMap<>();
        stockUpdater = new StockUpdater();
        stockService = StockExchange.getInstance().getStockService();
    }

    public void addStockListener(Stock stock, PriceChangeListener listener) {
        System.out.println("[PricingService] subscribing new listener to " + stock.getTicker());
        stockListMap.computeIfAbsent(stock, k -> new ArrayList<>()).add(listener);
    }

    public void removeStockListener(Stock stock, PriceChangeListener listener) {
        if (!stockListMap.containsKey(stock)) {
            System.out.println("[PricingService] unsubscribing listener from: " + stock.getTicker() + ", but the stock is not being watched");
            return;
        }
        System.out.println("[PricingService] unsubscribing listener from " + stock.getTicker());
        stockListMap.get(stock).remove(listener);
    }

    public void updatePrice(Stock stock, double price) {
        System.out.println("[PricingService] Price changed for " + stock.getTicker() + " to " + price);
        stock.setPrice(price);
        // Creating new ArrayList to avoid ConcurrentModificationException
        for (PriceChangeListener listener : new ArrayList<>(stockListMap.getOrDefault(stock, List.of()))) {
            listener.getPriceChangeNotification(stock);
        }
    }

    private class StockUpdater {
        private final Random random;
        public StockUpdater() {
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            Runnable task = this::tickPrices;
            scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
            random = new Random();
        }
        public void tickPrices() {
            for (Stock stock : stockService.getAllStocks()) {
                double currentPrice = stock.getPrice();
                double price = random.nextDouble(currentPrice * 0.9, currentPrice * 1.2);
                updatePrice(stock, price);
            }
        }
    }
}
