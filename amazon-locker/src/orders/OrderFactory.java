package orders;

import lockers.LockerSize;

public class OrderFactory {

    public static Order createOrder() {
        return new Order(false);
    }

    public static Order createReturnOrder(Order order) {
        return order.copy();
    }

    public static Package createPackage(LockerSize smallestLockerSize) {
        return new Package(smallestLockerSize);
    }
}
