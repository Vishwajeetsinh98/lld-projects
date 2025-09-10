package users;

import stocks.Stock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private final User user;
    private final List<Lot> lots;

    public Portfolio(User user) {
        this.user = user;
        lots = new ArrayList<>();
    }

    public void addLot(Stock stock, int quantity, double price) {
        Lot newLot = new Lot(stock, quantity, price, LocalDateTime.now());
        addLot(newLot);
    }

    public void addLot(Lot lot) {
        if(!lots.contains(lot)) {
            lots.add(lot);
            System.out.println("[Portfolio] Portfolio for user " + user.getId() + " added new lot: " + lot);
        }
    }

    public void removeLot(Stock stock, int quantity, double price) {
        Lot newLot = new Lot(stock, quantity, price, LocalDateTime.now());
        Lot lotToDelete = null;
        for (Lot lot : lots) {
            if (lot.equals(newLot)) {
                lotToDelete = lot;
                break;
            }
        }
        if (lotToDelete != null)
            lotToDelete.setQuantity(0);
        removeLot(lotToDelete);
    }

    public void removeLot(Lot lot) {
        if (!lots.contains(lot)) return;
        if (lot.getQuantity() != 0)
            throw new IllegalArgumentException("Cannot remove lot - non zero quantity!");
        lots.remove(lot);
        System.out.println("[Portfolio] Portfolio for user " + user.getId() + " removed lot: " + lot);
    }

    public void updateLot(Lot lot, int quantity) {
        if (!lots.contains(lot))
            throw new IllegalArgumentException("Invalid lot - not found!");
        System.out.println("[Portfolio] Portfolio for user " + user.getId() + " updating lot: " + lot + " new quantity: " + quantity);
        lot.setQuantity(quantity);
        if (lot.getQuantity() == 0)
            removeLot(lot);
    }

    public User getUser() { return user; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User: ").append(user.getId()).append("'s portfolio:");
        for (Lot lot : lots) {
            sb.append(lot);
            sb.append("\n");
        }
        return sb.toString();
    }

}
