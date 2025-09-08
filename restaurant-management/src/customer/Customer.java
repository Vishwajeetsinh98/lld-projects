package customer;

import branch.Branch;
import branch.staff.Server;
import order.Order;
import order.OrderItem;
import order.OrderItemState;
import reservation.Reservation;
import reservation.ReservationServiceUser;
import system.RestaurantManagementSystem;
import system.User;
import system.UserRole;

import java.time.LocalDateTime;
import java.util.List;

public class Customer implements User, ReservationServiceUser {
    // make reservation at branch
    // cancel reservation

    // order items
    // order bill
    // make payment

    private final String name;
    private final UserRole userRole;
    private RestaurantManagementSystem restaurantManagementSystem;
    private Server server;
    private boolean isSeated;

    public Customer(String name) {
        this.name = name;
        userRole = UserRole.CUSTOMER;
        isSeated = false;
    }

    @Override
    public UserRole getUserRole() { return userRole; }

    @Override
    public void bookReservation(Branch branch, LocalDateTime startTime, int numPeople) {
        restaurantManagementSystem.makeBooking(this, branch, startTime, this, numPeople);
    }

    @Override
    public void bookReservation(Branch branch, LocalDateTime startTime, Customer customer, int numPeople) {
        this.bookReservation(branch, startTime, numPeople);
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        restaurantManagementSystem.cancelBooking(this, reservation);
    }

    public void getMenu() {
        if (!isSeated) {
            System.out.println("[Customer] " + name + " not at table - requesting menu");
            return;
        }
        System.out.println("[Customer] " + name + " requesting menu.");
        server.giveMenu();
    }

    // TODO: provide modify and delete item options.
    public void orderItems(List<OrderItem> items) {
        if (!isSeated) {
            System.out.println("[Customer] " + name + " not at table - requesting to order.");
            return;
        }
        if (items.isEmpty())
            throw new IllegalArgumentException("Please add a few items to the order.");
        System.out.println("[Customer] " + name + " placing order.");

    }

    public void askForCheck() {
        if (!isSeated) {
            System.out.println("[Customer] " + name + " not at table - asking for check.");
            return;
        }
        System.out.println("[Customer] " + name + " requesting check.");
    }

    public void receiveCheck(double amount) {
        if (!isSeated) {
            System.out.println("[Customer] " + name + " not at table - received check.");
            return;
        }
        System.out.println("[Customer] " + name + " paying " + amount);
    }

    public void setRestaurantManagementSystem(RestaurantManagementSystem restaurantManagementSystem) { this.restaurantManagementSystem = restaurantManagementSystem; }

    public String getName() { return name; }

    public void setServer(Server server) { this.server = server; }

    public boolean isSeated() { return isSeated; }

    public void setSeated(boolean seated) { isSeated = seated; }
}
