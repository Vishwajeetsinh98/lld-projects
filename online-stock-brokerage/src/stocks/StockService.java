package stocks;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StockService {
    private final Map<String, Stock> stockTickerMap;

    public StockService() {
        stockTickerMap = new ConcurrentHashMap<>();
    }

    public void addStock(String ticker, String companyName, double price) {
        if (stockTickerMap.containsKey(ticker))
            throw new IllegalArgumentException("Stock with ticker " + ticker + " already exists!");
        System.out.println("[StockService] Adding new stock: " + ticker);
        Stock newStock = new Stock(ticker, companyName, price);
        stockTickerMap.put(ticker, newStock);
    }

    public void removeStock(String ticker) {
        if (!stockTickerMap.containsKey(ticker)) {
            System.out.println("[StockService] couldn't find stock: " + ticker);
            return;
        }
        System.out.println("[StockService] removing stock: " + ticker);
        stockTickerMap.remove(ticker);
    }

    public Stock getStock(String ticker) {
        return stockTickerMap.getOrDefault(ticker, null);
    }

    public List<Stock> getAllStocks() { return stockTickerMap.values().stream().toList(); }

}
