package dispenser;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GreedyStrategy implements CashDispenseStrategy {

    @Override
    public Map<Integer, Integer> dispense(Map<Integer, Integer> denominations, double amount) {
        List<Integer> descendingOrder = new ArrayList<>(denominations.keySet().stream().toList());
        descendingOrder.sort(Collections.reverseOrder());

        Map<Integer, Integer> originalMap = new ConcurrentHashMap<>(denominations);

        Map<Integer, Integer> ret = new HashMap<>();

        double originalAmount = amount;
        while (amount != 0) {
            boolean divided = false;
            for (int denom : descendingOrder) {
                System.out.println("[GreedyStrategy] checking for: " + denom + " amount: " + amount);
                if (amount % denom == 0 && denominations.getOrDefault(denom, 0) > 0) {
                    amount -= denom;
                    denominations.put(denom, denominations.get(denom) - 1);
                    divided = true;
                    ret.put(denom, ret.getOrDefault(denom, 0) + 1);
                    System.out.println("[GreedyStrategy] Gave $" + denom + " amount left: " + amount);
                    break;
                }
            }
            if (!divided) {
                // Put back the denominations removed
                denominations = new ConcurrentHashMap<>(originalMap);
                throw new IllegalArgumentException("Amount entered " + originalAmount + " unable to be dispensed");
            }
        }
        return ret;
    }
}
