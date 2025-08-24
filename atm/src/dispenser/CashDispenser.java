package dispenser;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CashDispenser {
    private final Map<Integer, Integer> denominations;
    private CashDispenseStrategy cashDispenseStrategy;

    public CashDispenser() {
        denominations = new ConcurrentHashMap<>();
        // This is for simulation purpose
        initializeDenominations();
        setCashDispenseStrategy(new GreedyStrategy());
    }

    public boolean dispenseMoney(double amount) {
        try {
            Map<Integer, Integer> notes = cashDispenseStrategy.dispense(denominations, amount);
            for (int note : notes.keySet()) {
                System.out.println("[CashDispenser] $" + note + " x " + notes.get(note));
            }
        } catch(Exception e) {
            return false;
        }
        return true;
    }

    public void initializeDenominations() {
        denominations.put(100, 100);
        denominations.put(200, 50);
        denominations.put(500, 100);
        denominations.put(2000, 100);
    }

    public void setCashDispenseStrategy(CashDispenseStrategy strategy) {
        cashDispenseStrategy = strategy;
    }
}
