package reservation;

import branch.Branch;
import branch.table.Table;
import customer.Customer;
import system.UserRole;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ReservationService {
    // create reservation
    // add table to reservation -> Only by receptionist / manager
    // cancel reservation

    private final Map<String, Reservation> allReservations;
    private final PriorityQueue<Reservation> upcomingReservations;
    public ReservationService() {
        allReservations = new ConcurrentHashMap<>();
        upcomingReservations = new PriorityQueue<>(new Comparator<Reservation>() {
            @Override
            public int compare(Reservation o1, Reservation o2) {
                if (o1.getStartTime().isAfter(o2.getStartTime()))
                    return 1;
                else if (o1.getStartTime().isBefore(o2.getStartTime()))
                    return -1;
                else return 0;
            }
        });
    }

    public void clear() {
        allReservations.clear();
        upcomingReservations.clear();
    }

    public Reservation addNewReservation(ReservationServiceUser user, Branch branch,
                                  LocalDateTime startTime, Customer customer, int numPeople) {
        if (!List.of(UserRole.CUSTOMER, UserRole.RECEPTIONIST, UserRole.MANAGER).contains(user.getUserRole()))
            throw new IllegalArgumentException("Only receptionist, customer or manager can create reservation.");
        System.out.println("[ReservationService] Creating new reservation for branch "
                                        + branch.getName() + " time: " + startTime + " customer: "
                                        + customer.getName());
        // check for conflicts
        Reservation newReservation = new Reservation(branch, startTime, customer, numPeople);
        upcomingReservations.add(newReservation);
        allReservations.put(newReservation.getId(), newReservation);
        return newReservation;
    }

    public void addTableToReservation(ReservationServiceUser user,
                                      Reservation reservation, Table table) {
        if (!List.of(UserRole.RECEPTIONIST, UserRole.MANAGER).contains(user.getUserRole()))
            throw new IllegalArgumentException("Only receptionist or manager can add table to reservation.");
        if (!allReservations.containsKey(reservation.getId()))
            throw new IllegalArgumentException("Reservation doesn't exist in database!");
        if (reservation.getReservationState() != ReservationState.RESERVED)
            throw new IllegalArgumentException("Can only add table to RESERVED reservation.");
        if (reservation.getTable() != null)
            throw new IllegalArgumentException("Table already assigned to reservation");
        System.out.println("[ReservationService] Setting table: " + table.getId() + " for reservation: " + reservation.getId());
        table.addReservation(reservation);
        reservation.setTable(table);
    }

    public void cancelReservation(ReservationServiceUser user, Reservation reservation) {
        if (!List.of(UserRole.CUSTOMER, UserRole.RECEPTIONIST, UserRole.MANAGER).contains(user.getUserRole()))
            throw new IllegalArgumentException("Only receptionist, customer or manager can cancel reservation.");
        if (user.getUserRole() == UserRole.CUSTOMER && reservation.getCustomer() != (Customer) user)
            throw new IllegalArgumentException("Customer can only cancel their own reservations!");
        if (!allReservations.containsKey(reservation.getId()))
            throw new IllegalArgumentException("Reservation doesn't exist in database!");
        System.out.println("[ReservationService] Canceling reservation " + reservation.getId());
        upcomingReservations.remove(reservation);
        reservation.getTable().removeReservation(reservation);
        reservation.setReservationState(ReservationState.CANCELED);
        reservation.setTable(null);
    }

    public PriorityQueue<Reservation> getUpcomingReservations() {
        return new PriorityQueue<>(upcomingReservations);
    }
}
