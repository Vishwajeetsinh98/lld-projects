package booking;

import hotel.Room;
import users.Guest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BookingBuilder {

    private Booking booking;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public BookingBuilder() {
        reset();
    }

    public void reset() {
        booking = new Booking();
        startTime = null;
        endTime = null;
    }

    public BookingBuilder withGuest(Guest guest) {
        booking.setGuest(guest);
        return this;
    }

    public BookingBuilder withRoom(Room room) {
        booking.setRoom(room);
        return this;
    }

    public BookingBuilder withStartDate(LocalDate startDate) {
        LocalTime time = LocalTime.of(14, 0, 0);
        startTime = startDate.atTime(time);

        if (endTime != null)
            booking.setReservation(new Reservation(startTime, endTime));

        return this;
    }

    public BookingBuilder withEndDate(LocalDate endDate) {
        LocalTime time = LocalTime.of(12, 0, 0);
        endTime = endDate.atTime(time);

        if (startTime != null)
            booking.setReservation(new Reservation(startTime, endTime));

        return this;
    }

    public BookingBuilder withReservation(Reservation reservation) {
        booking.setReservation(reservation);
        return this;
    }

    public BookingBuilder withRequests(String requests) {
        booking.setRequests(requests);
        return this;
    }

    public Booking build() {
        booking.getRoom().addReservation(booking.getReservation());
        return booking;
    }
}
