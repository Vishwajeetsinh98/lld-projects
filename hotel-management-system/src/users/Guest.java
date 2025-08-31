package users;

import booking.Booking;
import hotel.Branch;
import hotel.RoomType;

import java.time.LocalDate;

public class Guest extends User {
    public Guest(String userName, String password) {
        super(userName, password, UserRole.GUEST);
    }

    public Booking reserve(LocalDate start, LocalDate end, Branch branch, RoomType type) {
        return hotelManagementSystem.reserve(this, this, start, end, branch, type, null);
    }

    public Booking reserve(LocalDate start, LocalDate end, Branch branch, RoomType type, String requests) {
        return hotelManagementSystem.reserve(this, this, start, end, branch, type, requests);
    }

    public void cancelBooking(Booking booking) {
        hotelManagementSystem.cancel(this, booking);
    }

    // Will fail - added it for testing the authorization check
    public void checkIn(Booking booking) {
        hotelManagementSystem.checkIn(this, booking);
    }

}
