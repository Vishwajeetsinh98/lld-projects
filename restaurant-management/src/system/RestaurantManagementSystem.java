package system;

import branch.Branch;
import branch.menu.MenuItemFactory;
import branch.table.Table;
import customer.Customer;
import order.OrderService;
import reservation.Reservation;
import reservation.ReservationService;
import reservation.ReservationServiceUser;
import reservation.ReservationState;

import java.time.LocalDateTime;

public class RestaurantManagementSystem {
    private MenuItemFactory menuItemFactory;
    private Branch branch;
    private ReservationService reservationService;
    private OrderService orderService;

    public RestaurantManagementSystem() {}

    public Reservation makeBooking(ReservationServiceUser user, Branch branch, LocalDateTime startTime, Customer customer, int numPeople) {
        return reservationService.addNewReservation(user, branch, startTime, customer, numPeople);
    }

    public void cancelBooking(ReservationServiceUser user, Reservation reservation) {
        if (reservation.getReservationState() != ReservationState.RESERVED)
            throw new IllegalArgumentException("Can not cancel active/already canceled/completed bookings!");
        reservationService.cancelReservation(user, reservation);
    }

    public void assignTable(ReservationServiceUser user, Reservation reservation, Table table) {
        reservationService.addTableToReservation(user, reservation, table);
        if (LocalDateTime.now().isAfter(reservation.getStartTime())) {
            table.occupyTable(reservation.getCustomer());
            reservation.setReservationState(ReservationState.ACTIVE);
        }
    }

    public MenuItemFactory getMenuItemFactory() { return menuItemFactory; }

    public void setMenuItemFactory(MenuItemFactory menuItemFactory) { this.menuItemFactory = menuItemFactory; }

    public Branch getBranch() { return branch; }

    public void setBranch(Branch branch) { this.branch = branch; }

    public ReservationService getReservationService() { return reservationService; }

    public void setReservationService(ReservationService reservationService) { this.reservationService = reservationService; }

    public OrderService getOrderService() { return orderService; }

    public void setOrderService(OrderService orderService) { this.orderService = orderService; }
}
