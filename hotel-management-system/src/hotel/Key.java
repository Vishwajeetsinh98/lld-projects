package hotel;

import booking.Reservation;

import java.util.Objects;

public class Key {
    private String roomNumber;
    private final boolean isMasterKey;
    private Reservation reservation;
    private boolean isValid;

    public Key(boolean isMasterKey) {
        this.isMasterKey = isMasterKey;
    }

    public void activateKey(String roomNumber, Reservation reservation) {
        this.roomNumber = roomNumber;
        this.reservation = reservation;
        isValid = true;
    }

    public void invalidateKey() {
        reservation = null;
        roomNumber = null;
        isValid = false;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public boolean isMasterKey() {
        return isMasterKey;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public boolean isValid() {
        return isValid;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Key)) return false;
        return ((Key) o).roomNumber.equals(roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
