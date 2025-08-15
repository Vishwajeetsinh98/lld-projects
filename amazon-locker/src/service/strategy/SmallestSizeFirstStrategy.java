package service.strategy;


import lockers.Locker;
import lockers.LockerLocation;
import lockers.LockerSize;
import lockers.LockerStatus;
import orders.Order;
import orders.Package;
import service.AmazonLockerService;

import java.util.*;

public class SmallestSizeFirstStrategy implements LockerAssignmentStrategy {

    private AmazonLockerService amazonLockerService;
    public SmallestSizeFirstStrategy() {}

    @Override
    public List<String> assignLocker(Order order) {
        List<String> ret = new ArrayList<>();
        if (amazonLockerService == null)
            amazonLockerService = AmazonLockerService.getInstance();
        LockerLocation lockerLocation = amazonLockerService.getLockerLocationById(order.getLockerLocationId());

        Map<Locker, Package> assignmentMap = new HashMap<>();

        for (Package aPackage : order.getPackages()) {
            LockerSize smallest = aPackage.getSmallestLockerSize();
            boolean assigned = false;
            for (LockerSize size : LockerSize.values()) {
                if (size.compareTo(smallest) >= 0) {
                    for (Locker locker : lockerLocation.getLockerBySize(size)) {
                        if (locker.getLockerStatus() == LockerStatus.VACANT) {
                            assigned = true;
                            assignmentMap.put(locker, aPackage);
                            break;
                        }
                    }
                    if (assigned) break;
                }
            }
            if (!assigned) {
                System.out.println("[SmallestSizeFirstStrategy] Unable to assign locker to package " + aPackage.getId() + " " + aPackage.getSmallestLockerSize());
                System.out.println("[SmallestSizeFirstStrategy] Not assigning entire order");
                return null;
            }
        }

        assignmentMap.forEach((locker, aPackage) -> {
            locker.setLockerPackage(aPackage);
            locker.setLockerStatus(LockerStatus.ASSIGNED);
            aPackage.setLockerId(locker.getId());
            ret.add(locker.getId());
        });
        return ret;
    }
}
