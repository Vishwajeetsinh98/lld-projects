package system.roomstrategy;

import booking.Reservation;
import hotel.Branch;
import hotel.Room;
import hotel.RoomType;
import system.HotelManagementSystem;

import java.time.LocalDate;
import java.util.List;

public class DefaultRoomAssignmentStrategy implements RoomAssignmentStrategy {

    @Override
    public Room assign(HotelManagementSystem system, Branch branch, RoomType type, Reservation reservation) {

        List<Room> rooms = branch.getRoomsByType(type);
        for (Room room : rooms) {
            // Today's reservation:
            if(reservation.startTime().toLocalDate().isEqual(LocalDate.now())) {
                if (room.isOccupied()) continue;
                else return room;
            }

            // Future date reservation:
            if (room.getFutureReservations().isEmpty())
                return room;
            boolean conflict = false;
            for (Reservation fut : room.getFutureReservations()) {
                conflict = conflict || fut.hasConflict(reservation);
            }
            if (!conflict) return room;
        }

        return null;
    }
}
