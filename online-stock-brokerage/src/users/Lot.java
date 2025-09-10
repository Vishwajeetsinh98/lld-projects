package users;

import stocks.Stock;

import java.time.LocalDateTime;
import java.util.Objects;

public class Lot {
    private final Stock stock;
    private int quantity;
    private final double purchasePrice;
    private final LocalDateTime purchaseTime;

    public Lot(Stock stock, int quantity, double purchasePrice, LocalDateTime purchaseTime) {
        this.stock = stock;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.purchaseTime = purchaseTime;
    }

    public Stock getStock() { return stock; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPurchasePrice() { return purchasePrice; }

    public LocalDateTime getPurchaseTime() { return purchaseTime; }

    @Override
    public String toString() {
        return "Lot{" +
                "stock=" + stock.getTicker() +
                ", quantity=" + quantity +
                ", purchasePrice=" + purchasePrice +
                ", purchaseTime=" + purchaseTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lot lot)) return false;
        return quantity == lot.quantity &&
                Double.compare(purchasePrice, lot.purchasePrice) == 0 &&
                    Objects.equals(stock, lot.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stock, quantity, purchasePrice);
    }
}
