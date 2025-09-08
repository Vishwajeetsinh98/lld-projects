package branch.staff;

import branch.Branch;
import branch.table.Table;
import branch.table.TableAssignmentStrategy;
import branch.table.TableAssignmentUser;
import customer.Customer;
import reservation.Reservation;
import reservation.ReservationServiceUser;
import system.RestaurantManagementSystem;
import system.User;
import system.UserRole;

import java.time.LocalDateTime;

public class Receptionist implements User, ReservationServiceUser, TableAssignmentUser {
    // make reservation for customer at home branch
    // assign table to customer
    // cancel reservation
    // change reservation status -> occupy table
    private final String name;
    private final UserRole userRole;
    private final Branch branch;
    private RestaurantManagementSystem restaurantManagementSystem;
    private TableAssignmentStrategy tableAssignmentStrategy;

    public Receptionist(String name, Branch branch) {
        this.name = name;
        userRole = UserRole.RECEPTIONIST;
        this.branch = branch;
    }

    public String getName() { return name; }
    public Branch getBranch() { return branch; }
    public void setRestaurantManagementSystem(RestaurantManagementSystem restaurantManagementSystem) { this.restaurantManagementSystem = restaurantManagementSystem; }
    public void setTableAssignmentStrategy(TableAssignmentStrategy tableAssignmentStrategy) { this.tableAssignmentStrategy = tableAssignmentStrategy; }

    @Override
    public UserRole getUserRole() { return userRole; }

    @Override
    public void bookReservation(Branch branch, LocalDateTime startTime, int numPeople) {
        throw new IllegalArgumentException("Please provide customer as well");
    }

    @Override
    public void bookReservation(Branch branch, LocalDateTime startTime, Customer customer, int numPeople) {
        Reservation reservation = restaurantManagementSystem.makeBooking(this, branch, startTime, customer, numPeople);
        assignTable(reservation);
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        restaurantManagementSystem.cancelBooking(this, reservation);
    }

    @Override
    public void assignTable(Reservation reservation) {
        Table table = tableAssignmentStrategy.assign(reservation);
        if (table == null) {
            System.out.println("[Receptionist] Apologies, no tables available at the moment.");
            return;
        }
        System.out.println("[Receptionist] Assigning table: " + table.getId() + " to reservation: " + reservation.getId());
        restaurantManagementSystem.assignTable(this, reservation, table);
    }

    @Override
    public void assignSpecificTable(Reservation reservation, Table table) {
        restaurantManagementSystem.assignTable(this, reservation, table);
    }
}
