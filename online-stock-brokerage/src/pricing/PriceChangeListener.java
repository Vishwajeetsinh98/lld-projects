package pricing;

import stocks.Stock;

public interface PriceChangeListener {
    void getPriceChangeNotification(Stock stock);
}
