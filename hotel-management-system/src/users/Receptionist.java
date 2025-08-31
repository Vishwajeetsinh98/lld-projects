package users;

import booking.Booking;
import hotel.Branch;
import hotel.RoomType;

import java.time.LocalDate;

public class Receptionist extends Staff {
    public Receptionist(String userName, String password) {
        super(userName, password, UserRole.RECEPTIONIST);
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
}
