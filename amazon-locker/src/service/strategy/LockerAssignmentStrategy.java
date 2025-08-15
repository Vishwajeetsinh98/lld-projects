package service.strategy;

import lockers.Locker;
import orders.Order;

import java.util.List;

public interface LockerAssignmentStrategy {
    public List<String> assignLocker(Order order);
}
