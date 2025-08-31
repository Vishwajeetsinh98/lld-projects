package users;

import hotel.Branch;

public class HouseKeeping extends Staff {
    public HouseKeeping(String userName, String password) {
        super(userName, password, UserRole.HOUSEKEEPING);
    }

    public void cleanRoom(Branch branch, String roomNumber) {
        hotelManagementSystem.cleanRoom(this, branch, roomNumber, this);
    }
}
