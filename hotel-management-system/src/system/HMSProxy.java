package system;

import booking.Booking;
import booking.BookingStatus;
import hotel.Branch;
import hotel.CleanupLog;
import hotel.RoomType;
import users.Guest;
import users.HouseKeeping;
import users.User;
import users.UserRole;

import java.time.LocalDate;

public class HMSProxy {
    private final HotelManagementSystem system;

    public HMSProxy(HotelManagementSystem system) {
        this.system = system;
    }

    private void isUserAuthorizedTo(UserRole minimumClearance, UserRole userRole) {
        if (minimumClearance == UserRole.HOUSEKEEPING)
            if (!(userRole == UserRole.HOUSEKEEPING || userRole == UserRole.MANAGER))
                throw new IllegalArgumentException("User not authorized for this action!");
        if (userRole.compareTo(minimumClearance) < 0)
            throw new IllegalArgumentException("User not authorized for this action!");
    }

    public Booking reserve(User user, Guest guest,
                           LocalDate start, LocalDate end,
                           Branch branch, RoomType type, String requests) {
        isUserAuthorizedTo(UserRole.GUEST, user.getRole());
        return system.createBooking(guest, start, end, branch, type, requests);
    }

    public void cancel(User user, Booking booking) {
        isUserAuthorizedTo(UserRole.GUEST, user.getRole());
        system.cancelBooking(booking);
    }

    public void checkIn(User user, Booking booking) {
        isUserAuthorizedTo(UserRole.RECEPTIONIST, user.getRole());
        system.checkIn(booking);
    }

    public void checkOut(User user, Booking booking) {
        isUserAuthorizedTo(UserRole.RECEPTIONIST, user.getRole());

        system.checkOut(booking);
    }

    public void cleanRoom(User user, Branch branch,
                          String roomNumber, HouseKeeping cleaner) {
        isUserAuthorizedTo(UserRole.HOUSEKEEPING, user.getRole());

        system.createCleanupLog(branch, roomNumber, cleaner);
    }

    public void getBookings(User user) {
        isUserAuthorizedTo(UserRole.MANAGER, user.getRole());

        for (Booking booking : system.getBookings()) {
            System.out.println("[HMSProxy] Booking: ");
            System.out.println("Start: " + booking.getReservation().startTime() + " end: " + booking.getReservation().endTime());
            System.out.println("Status: " + booking.getStatus());
            if (booking.getStatus() == BookingStatus.RESERVED || booking.getStatus() == BookingStatus.ACTIVE) {
                System.out.println("Room: " + booking.getRoom().getRoomNumber() + " type: " + booking.getRoom().getRoomType());
            }
            if (booking.getStatus() != BookingStatus.CANCELED)
                System.out.println("Amount: " + booking.getBookingAmount());
        }
    }

    public void getCleanupLog(User user, Branch branch) {
        isUserAuthorizedTo(UserRole.MANAGER, user.getRole());

        for (CleanupLog log : branch.getCleanupLogs()) {
            System.out.println(log);
        }
    }

    // Singleton:
    private static final class Holder {
        private static final HMSProxy INSTANCE = new HMSProxy(HotelManagementSystem.getInstance());
    }

    public static HMSProxy getInstance() {
        return Holder.INSTANCE;
    }
}