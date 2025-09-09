package users;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private final User user;
    private final List<Lot> lots;

    public Portfolio(User user) {
        this.user = user;
        lots = new ArrayList<>();
    }

    public void addLot(Lot lot) {
        if(!lots.contains(lot)) {
            lots.add(lot);
            System.out.println("[Portfolio] Portfolio for user " + user.getId() + " added new lot: " + lot);
        }
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

}
