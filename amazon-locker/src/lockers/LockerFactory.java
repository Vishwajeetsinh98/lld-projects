package lockers;

import java.time.LocalTime;

public class LockerFactory {

    public static LockerLocation createDefaultLockerLocation(String address) {
        return new LockerLocation(address);
    }

    public static LockerLocation createLockerLocationWithTimings(String address,
                                                                 LocalTime open,
                                                                 LocalTime close) {
        return new LockerLocation(address, open, close);
    }

    public static Locker createSmallLocker() {
        return new Locker(LockerSize.SMALL);
    }

    public static Locker createMediumLocker() {
        return new Locker(LockerSize.MEDIUM);
    }

    public static Locker createLargeLocker() {
        return new Locker(LockerSize.LARGE);
    }

    public static Locker createXLLocker() {
        return new Locker(LockerSize.XL);
    }

    public static Locker createXXLLocker() {
        return new Locker(LockerSize.XXL);
    }
}
