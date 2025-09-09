package stocks;

import java.util.Objects;

public class Stock {
    private final String ticker;
    private final String companyName;
    private double price;

    public Stock(String ticker, String companyName) {
        this.ticker = ticker;
        this.companyName = companyName;
        price = 0.0;
    }

    public Stock(String ticker, String companyName, double price) {
        this(ticker, companyName);
        this.price = price;
    }

    public String getTicker() { return ticker; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public String getCompanyName() { return companyName; }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Stock)) return false;
        return ((Stock) o).ticker.equals(ticker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticker);
    }
}
