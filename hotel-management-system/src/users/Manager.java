package users;

import booking.Booking;
import hotel.Branch;
import hotel.RoomType;

import java.time.LocalDate;

public class Manager extends Staff {
    public Manager(String userName, String password) {
        super(userName, password, UserRole.MANAGER);
    }

    public Booking reserve(Guest guest, LocalDate start, LocalDate end, Branch branch, RoomType type) {
        return hotelManagementSystem.reserve(this, guest, start, end, branch, type, null);
    }

    public Booking reserve(Guest guest, LocalDate start, LocalDate end, Branch branch, RoomType type, String requests) {
        return hotelManagementSystem.reserve(this, guest, start, end, branch, type, requests);
    }

    public void cancelBooking(Booking booking) {
        hotelManagementSystem.cancel(this, booking);
    }

    public void checkIn(Booking booking) {
        hotelManagementSystem.checkIn(this, booking);
    }

    public void checkOut(Booking booking) {
        hotelManagementSystem.checkOut(this, booking);
    }

    public void cleanRoom(Branch branch, String roomNumber, HouseKeeping cleaner) {
        hotelManagementSystem.cleanRoom(this, branch, roomNumber, cleaner);
    }

    public void getBookings() {
        hotelManagementSystem.getBookings(this);
    }

    public void getCleanupLogs(Branch branch) {
        hotelManagementSystem.getCleanupLog(this, branch);
    }
}
