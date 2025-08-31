package hotel;

import users.HouseKeeping;
import users.Manager;
import users.Receptionist;
import users.Staff;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Branch {

    private final String branchId;
    private final List<Staff> allStaff;
    private final Map<RoomType, List<Room>> roomTypeMap;
    private final Map<String, Room> roomIdMap;
    private final List<Key> allKeys;
    private final List<CleanupLog> cleanupLogs;

    public Branch(String branchId) {
        this.branchId = branchId;
        allStaff = new ArrayList<>();
        roomTypeMap = new ConcurrentHashMap<>();
        roomIdMap = new ConcurrentHashMap<>();
        allKeys = new ArrayList<>();
        cleanupLogs = new ArrayList<>();
        initializeStaff();
        addMultipleRooms(10, 10, 10, 10);
    }

    private void initializeStaff() {
        // 1 manager, 5 receptionists, 10 housekeeping for now.
        Staff manager = new Manager("testmgr", "1234");
        allStaff.add(manager);
        for (int i = 0;i < 5;i++) {
            Staff receptionist = new Receptionist("testrcp_" + (i + 1), "1234");
            allStaff.add(receptionist);
        }
        for (int i = 0; i < 10;i++) {
            Staff housekeeping = new HouseKeeping("testhk_" + (i + 1), "1234");
            allStaff.add(housekeeping);
        }
        // Create 5 master keys as well
        for(int i = 0;i < 5;i++) {
            Key key = new Key(true);
            allKeys.add(key);
        }
    }

    public boolean checkStaffExists(Staff staff) {
        return allStaff.contains(staff);
    }

    public Room getRoomByNumber(String roomNumber) {
        return roomIdMap.getOrDefault(roomNumber, null);
    }

    public List<Room> getRoomsByType(RoomType roomType) {
        return roomTypeMap.getOrDefault(roomType, new ArrayList<>());
    }

    public void addRoom(RoomType type) {
        Room newRoom = RoomFactory.createRoom(type);
        if (!roomTypeMap.containsKey(type))
            roomTypeMap.put(type, new ArrayList<>());
        roomTypeMap.get(type).add(newRoom);
        roomIdMap.put(newRoom.getRoomNumber(), newRoom);
    }

    public void addMultipleRooms(int stdCount, int dlxCount, int fsCount, int bsCount) {
        for (int i = 0;i < stdCount;i++)
            addRoom(RoomType.STANDARD);
        for (int i = 0;i < dlxCount;i++)
            addRoom(RoomType.DELUXE);
        for (int i = 0;i < fsCount;i++)
            addRoom(RoomType.FAMILY_SUITE);
        for (int i = 0;i < bsCount;i++)
            addRoom(RoomType.BUSINESS_SUITE);
    }

    public void addCleanupLogEntry(CleanupLog entry) {
        cleanupLogs.add(entry);
    }

    public List<CleanupLog> getCleanupLogs() {
        return cleanupLogs;
    }

    public String getBranchId() {
        return branchId;
    }

    public Manager getManager() {
        for (Staff staff : allStaff) {
            if (staff instanceof Manager)
                return (Manager) staff;
        }
        return null;
    }

    public Receptionist getReceptionist() {
        for (Staff staff : allStaff) {
            if (staff instanceof Receptionist)
                return (Receptionist) staff;
        }
        return null;
    }

    public HouseKeeping getHouseKeeping() {
        for (Staff staff : allStaff) {
            if (staff instanceof HouseKeeping)
                return (HouseKeeping) staff;
        }
        return null;
    }
}