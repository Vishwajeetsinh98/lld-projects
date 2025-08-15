package orders;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int autoIncId = 0;
    private static int autoIntIdRet = 0;
    private final String id;
    private final List<Package> packages;
    private String lockerLocationId;
    private final boolean isReturnOrder;
    private int orderPIN;
    private boolean isDelivered;

    public Order(boolean isReturnOrder) {
        this.id = getNextId(isReturnOrder);
        this.packages = new ArrayList<>();
        this.isReturnOrder = isReturnOrder;
        this.orderPIN = -1;
        this.isDelivered = false;
    }

    public Order(boolean isReturnOrder, List<Package> packages) {
        this(isReturnOrder);
        this.packages.addAll(packages);
    }

    private Order (Order order) {
        this.id = getNextId(!order.isReturnOrder);
        this.packages = new ArrayList<>(order.packages);
        this.lockerLocationId = order.lockerLocationId;
        this.isReturnOrder = !order.isReturnOrder;
        this.orderPIN = -1;
        this.isDelivered = false;
    }

    public void assignLockerLocation(String lockerLocationId) {
        this.lockerLocationId = lockerLocationId;
    }

    public String getId() {
        return id;
    }

    public void addPackage(Package aPackage) {
        packages.add(aPackage);
    }

    public List<Package> getPackages() {
        return packages;
    }

    public String getLockerLocationId() {
        return lockerLocationId;
    }

    public boolean isReturnOrder() {
        return isReturnOrder;
    }

    public int getOrderPIN() {
        return orderPIN;
    }

    public void setOrderPIN(int orderPIN) {
        this.orderPIN = orderPIN;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public Order copy() {
        return new Order(this);
    }

    private static String getNextId(boolean isReturnOrder) {
        return (isReturnOrder ? "R_" : "O_") + (isReturnOrder ? ++autoIntIdRet : ++autoIncId);
    }
}
