import lockers.LockerFactory;
import lockers.LockerLocation;
import lockers.LockerSize;
import orders.Order;
import orders.OrderFactory;
import service.AmazonLockerService;

public class Main {
    public static void main(String[] args) throws Exception {
        LockerLocation location = LockerFactory.createDefaultLockerLocation("test");
        for (int i = 0;i < 5;i++) {
            location.addLocker(LockerFactory.createSmallLocker());
            location.addLocker(LockerFactory.createMediumLocker());
            location.addLocker(LockerFactory.createLargeLocker());
            location.addLocker(LockerFactory.createXLLocker());
            location.addLocker(LockerFactory.createXXLLocker());
        }

        AmazonLockerService lockerService = AmazonLockerService.getInstance();

        lockerService.addLockerLocation(location);
        Order order = OrderFactory.createOrder();
        order.assignLockerLocation(location.getId());
        order.addPackage(OrderFactory.createPackage(LockerSize.MEDIUM));
        order.addPackage(OrderFactory.createPackage(LockerSize.SMALL));
        order.addPackage(OrderFactory.createPackage(LockerSize.LARGE));
        lockerService.addOrder(order);


        int pin = lockerService.assignOrderToLockers(order);
        lockerService.collectOrder(order, pin);

        Order returnOrder = lockerService.createReturnRequest(order);
        lockerService.returnOrder(returnOrder, returnOrder.getOrderPIN());
    }
}
