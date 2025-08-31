package system.roomstrategy;

import booking.Reservation;
import hotel.Branch;
import hotel.Room;
import hotel.RoomType;
import system.HotelManagementSystem;

import java.time.LocalDate;

public interface RoomAssignmentStrategy {
    public Room assign(HotelManagementSystem system, Branch branch, RoomType type, Reservation reservation);
}
