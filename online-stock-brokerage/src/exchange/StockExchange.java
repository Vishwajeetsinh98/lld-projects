package exchange;

import pricing.PricingService;
import stocks.StockService;

public class StockExchange {
    private StockService stockService;
    private PricingService pricingService;
    private StockExchange() {}

    public void setStockService(StockService stockService) { this.stockService = stockService; }
    public StockService getStockService() { return stockService; }

    public void setPricingService(PricingService pricingService) { this.pricingService = pricingService; }
    public PricingService getPricingService() { return pricingService; }

    // Singleton:
    private static final class Holder {
        private static final StockExchange INSTANCE = new StockExchange();
    }
    public static StockExchange getInstance() {
        return Holder.INSTANCE;
    }
}
