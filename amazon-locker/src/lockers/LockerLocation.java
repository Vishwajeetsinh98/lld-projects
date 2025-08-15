package lockers;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LockerLocation {

    private static int autoIncId = 0;

    private final String id;
    private final String address;
    private final Map<String, Locker> lockerIdMap;
    private final Map<LockerSize, Set<Locker>> lockerSizeMap;
    private LocalTime openTime;
    private LocalTime closeTime;

    public LockerLocation(String address) {
        this.id = getNextId();
        this.address = address;
        this.lockerIdMap = new ConcurrentHashMap<>();
        this.lockerSizeMap = new ConcurrentHashMap<>();
        this.openTime = LocalTime.of(8, 0);
        this.closeTime = LocalTime.of(16, 0);
    }

    public LockerLocation(String address,
                          LocalTime openTime,
                          LocalTime closeTime) {
        this(address);
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public void addLocker(Locker locker) {
        if(!lockerIdMap.containsKey(locker.getId())) {
            lockerIdMap.put(locker.getId(), locker);
            if (!lockerSizeMap.containsKey(locker.getLockerSize()))
                lockerSizeMap.put(locker.getLockerSize(), new HashSet<>());
            lockerSizeMap.get(locker.getLockerSize()).add(locker);
        }
    }

    public List<Locker> getLockerBySize(LockerSize size) {
        return new ArrayList<>(lockerSizeMap.getOrDefault(size, new HashSet<>()));
    }

    public Locker getLockerById(String lockerId) {
        return lockerIdMap.getOrDefault(lockerId, null);
    }

    public List<Locker> getAllLockers() {
        return new ArrayList<>(lockerIdMap.values());
    }

    public boolean isOpen() {
        LocalTime now = LocalTime.now();
        return now.isAfter(openTime) && now.isBefore(closeTime);
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    private static String getNextId() {
        return "LL_" + ++autoIncId;
    }
}
