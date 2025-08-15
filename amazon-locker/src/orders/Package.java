package orders;

import lockers.LockerSize;

public class Package {
    private static int autoIncId = 0;

    private final String id;
    private final LockerSize smallestLockerSize;
    private String lockerId;

    public Package(LockerSize smallestLockerSize) {
        this.id = getNextId();
        this.smallestLockerSize = smallestLockerSize;
    }

    public String getId() {
        return id;
    }

    public LockerSize getSmallestLockerSize() {
        return smallestLockerSize;
    }

    public String getLockerId() {
        return lockerId;
    }

    public void setLockerId(String lockerId) {
        this.lockerId = lockerId;
    }

    private static String getNextId() {
        return "P_" + ++autoIncId;
    }
}
