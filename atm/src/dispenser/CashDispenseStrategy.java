package dispenser;

import java.util.List;
import java.util.Map;

public interface CashDispenseStrategy {
    public Map<Integer, Integer> dispense(Map<Integer, Integer> denominations, double amount);
}
