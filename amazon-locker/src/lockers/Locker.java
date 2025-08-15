package lockers;

import orders.Package;

public class Locker {
    private static int autoIncId = 0;
    private final String id;
    private final LockerSize lockerSize;
    private Package lockerPackage;
    private LockerStatus lockerStatus;

    public Locker(LockerSize lockerSize) {
        this.id = getNextId();
        this.lockerSize = lockerSize;
        this.lockerPackage = null;
        this.lockerStatus = LockerStatus.VACANT;
    }

    public String getId() {
        return id;
    }

    public LockerSize getLockerSize() {
        return lockerSize;
    }

    public Package getLockerPackage() {
        return lockerPackage;
    }

    public void setLockerPackage(Package lockerPackage) {
        this.lockerPackage = lockerPackage;
    }

    public LockerStatus getLockerStatus() {
        return lockerStatus;
    }

    public void setLockerStatus(LockerStatus status) {
        lockerStatus = status;
    }

    public boolean equals(Locker l1) {
        return l1.id.equals(id);
    }

    private static String getNextId() {
        return "L_" + ++autoIncId;
    }
}
