package reservation;

import branch.Branch;
import customer.Customer;
import system.User;

import java.time.LocalDateTime;

public interface ReservationServiceUser extends User {
    public void bookReservation(Branch branch, LocalDateTime startTime, int numPeople);
    public void bookReservation(Branch branch, LocalDateTime startTime, Customer customer, int numPeople);
    public void cancelReservation(Reservation reservation);
}
