import booking.Booking;
import hotel.Branch;
import hotel.RoomType;
import system.HotelManagementSystem;
import users.Guest;
import users.HouseKeeping;
import users.Manager;
import users.Receptionist;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Guest guest = new Guest("testGuest", "1234");
        Branch branch = new Branch("b1");
        HotelManagementSystem system = HotelManagementSystem.getInstance();
        system.addBranch(branch);

        Manager manager = branch.getManager();
        Receptionist receptionist = branch.getReceptionist();
        HouseKeeping houseKeeping = branch.getHouseKeeping();

        Booking b1 = guest.reserve(LocalDate.of(2025, 9, 10), LocalDate.of(2025, 9, 12), branch, RoomType.DELUXE);
        Booking b2 = guest.reserve(LocalDate.of(2025, 9, 10), LocalDate.of(2025, 9, 12), branch, RoomType.STANDARD);
        guest.cancelBooking(b2);

        manager.getBookings();
        receptionist.checkIn(b1);
//        receptionist.checkIn(b2);
        receptionist.checkOut(b1);

        houseKeeping.cleanRoom(branch, b1.getRoom().getRoomNumber());

        manager.getBookings();
        manager.getCleanupLogs(branch);

        guest.checkIn(b1);

    }
}