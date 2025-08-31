package booking;

import hotel.Room;
import users.Guest;

public class Booking {
    private Room room;
    private Reservation reservation;
    private BookingStatus status;
    private String requests;
    private Guest guest;
    private double bookingAmount;
    private double paidSoFar;

    public Booking() {
        status = BookingStatus.RESERVED;
        bookingAmount = 0.0;
        paidSoFar = 0.0;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String getRequests() {
        return requests;
    }

    public void setRequests(String requests) {
        this.requests = requests;
    }

    public double getBookingAmount() {
        return bookingAmount;
    }

    public void setBookingAmount(double bookingAmount) {
        this.bookingAmount = bookingAmount;
    }

    public double getPaidSoFar() {
        return paidSoFar;
    }

    public void setPaidSoFar(double paidSoFar) {
        this.paidSoFar = paidSoFar;
    }
}
