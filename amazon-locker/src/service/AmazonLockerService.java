package service;

import lockers.Locker;
import lockers.LockerLocation;
import lockers.LockerStatus;
import orders.Order;
import orders.OrderFactory;
import orders.Package;
import service.strategy.LockerAssignmentStrategy;
import service.strategy.SmallestSizeFirstStrategy;
import util.PINService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AmazonLockerService {

    private final Map<String, LockerLocation> lockerLocationMap;
    private final Map<String, Order> orderMap;
    private LockerAssignmentStrategy assignmentStrategy;

    private AmazonLockerService() {
        this.lockerLocationMap = new ConcurrentHashMap<>();
        this.orderMap = new ConcurrentHashMap<>();
        this.assignmentStrategy = new SmallestSizeFirstStrategy();
    }

    public int assignOrderToLockers(Order order) {
        List<String> ret = assignmentStrategy.assignLocker(order);
        if (ret == null) {
            System.out.println("[AmazonLockerService] Could not assign order");
            return -1;
        }

        System.out.println("[AmazonLockerService] Assigned Lockers:");
        for (String lockerId : ret) {
            System.out.println(lockerId);
        }
        if (order.isReturnOrder()) return -1;
        int pin = PINService.getInstance().getOtp();
        order.setOrderPIN(pin);
        System.out.println("[AmazonLockerService] PIN assigned: " + pin);
        return pin;
    }

    private boolean validatePIN(int userPin, int orderPin) {
        return PINService.getInstance().validateOtp(userPin) && orderPin == userPin;
    }

    public void collectOrder(Order order, int pin) throws Exception {
        if (!validatePIN(pin, order.getOrderPIN())) {
            System.out.println("[AmazonLockerService] Incorrect PIN!");
            return;
        }

        LockerLocation lockerLocation = lockerLocationMap.get(order.getLockerLocationId());

        if (!lockerLocation.isOpen()) {
            System.out.println("[AmazonLockerService] Locker location unserviceable. Please try from: " + lockerLocation.getOpenTime() + " - " + lockerLocation.getCloseTime());
            return;
        }

        for (Package aPackage : order.getPackages()) {
            System.out.println("[AmazonLockerService] Returning Package " + aPackage.getId() + " from: " + aPackage.getLockerId());
            Locker locker = lockerLocation.getLockerById(aPackage.getLockerId());
            if (locker == null) {
                throw new Exception("Locker not found!");
            }
            locker.setLockerPackage(null);
            locker.setLockerStatus(LockerStatus.VACANT);
        }
        order.setDelivered(true);
        PINService.getInstance().invalidateOtp(pin);
    }

    public Order createReturnRequest(Order order) {
        System.out.println("[AmazonLockerService] Creating return for " + order.getId());
        Order returnOrder = OrderFactory.createReturnOrder(order);
        addOrder(returnOrder);
        System.out.println("[AmazonLockerService] Return order id: " + returnOrder.getId());
        int pin = PINService.getInstance().getOtp();
        returnOrder.setOrderPIN(pin);
        System.out.println("[AmazonLockerService] PIN for return: " + pin);
        return returnOrder;
    }

    public void returnOrder(Order order, int pin) {
        if (!order.isReturnOrder()) {
            System.out.println("[AmazonLockerService] Not a valid return order!");
            return;
        }
        if (!validatePIN(pin, order.getOrderPIN())) {
            System.out.println("[AmazonLockerService] Incorrect PIN!");
            return;
        }
        assignOrderToLockers(order);
        order.setDelivered(true);
    }

    public void setAssignmentStrategy(LockerAssignmentStrategy assignmentStrategy) {
        this.assignmentStrategy = assignmentStrategy;
    }

    public void addLockerLocation(LockerLocation lockerLocation) {
        lockerLocationMap.put(lockerLocation.getId(), lockerLocation);
    }

    public LockerLocation getLockerLocationById(String lockerLocationId) {
        return lockerLocationMap.getOrDefault(lockerLocationId, null);
    }

    public void addOrder(Order order) {
        orderMap.put(order.getId(), order);
    }

    public Order getOrderById(String orderId) {
        return orderMap.getOrDefault(orderId, null);
    }


    // Singleton:
    private static class Holder {
        private static final AmazonLockerService INSTANCE = new AmazonLockerService();
    }

    public static AmazonLockerService getInstance() {
        return Holder.INSTANCE;
    }
}
