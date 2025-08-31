package hotel;

import booking.Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String roomNumber;
    private boolean occupied;
    private final RoomType roomType;
    private final String description;
    private Reservation currentReservation;
    private final List<Reservation> futureReservations;

    public Room(String roomNumber, RoomType roomType, String description) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.description = description;
        futureReservations = new ArrayList<>();
    }

    private boolean checkKeyValid(Key key) {
        if (!key.getRoomNumber().equals(roomNumber))
            return false;

        return currentReservation.contains(key.getReservation());
    }

    public void checkInRoom(Reservation reservation) {
        currentReservation = reservation;
        futureReservations.remove(reservation);
        occupied = true;
    }

    public void checkOutRoom() {
        currentReservation = null;
        occupied = false;
    }

    public void addReservation(Reservation reservation) {
        futureReservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        futureReservations.remove(reservation);
    }

    public List<Reservation> getFutureReservations() {
        return futureReservations;
    }

    public boolean openRoom(Key key) {
        if (!isOccupied() && key.isMasterKey()) {
            System.out.println("[Room] Vacant " + roomNumber + " opening for staff");
            return true;
        }
        if (key.isMasterKey() || checkKeyValid(key)) {
            System.out.println("[Room] " + roomNumber + " opening with key");
            return true;
        }
        System.out.println("[Room] " + roomNumber + " tried to open with invalid/expired key");
        return false;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public String getDescription() {
        return description;
    }
}
