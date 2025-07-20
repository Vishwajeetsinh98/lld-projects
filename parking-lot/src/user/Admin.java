package user;

import enums.UserType;
import parkinglot.ParkingLot;

public class Admin extends User {
    public Admin(String userName, String password, UserType userType) {
        super(userName, password, userType);
    }

    public void addEntry() {
        ParkingLot.getInstance().addEntry();
    }

    public void addExit() {
        ParkingLot.getInstance().addExit();
    }

    public void removeEntry(String entryId) {
        ParkingLot.getInstance().removeEntry(entryId);
    }

    public void removeExit(String exitId) {
        ParkingLot.getInstance().removeExit(exitId);
    }

    public void addDisplayBoard() {
        ParkingLot.getInstance().addDisplayBoard();
    }

}
