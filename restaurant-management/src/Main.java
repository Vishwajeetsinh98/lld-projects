import branch.Branch;
import branch.menu.Menu;
import branch.menu.MenuItem;
import branch.menu.MenuItemFactory;
import branch.menu.MenuSection;
import branch.staff.Receptionist;
import branch.table.DefaultAssignmentStrategy;
import branch.table.Table;
import branch.table.TableAssignmentStrategy;
import customer.Customer;
import order.Order;
import order.OrderItem;
import order.OrderService;
import reservation.Reservation;
import reservation.ReservationService;
import system.RestaurantManagementSystem;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ReservationService reservationService = new ReservationService();
        OrderService orderService = new OrderService();
        TableAssignmentStrategy tableAssignmentStrategy = new DefaultAssignmentStrategy();
        Branch branch = new Branch("BRANCH");
        tableAssignmentStrategy.setBranch(branch);
        Table table = new Table();
        Table table1 = new Table();
        branch.addTable(table);
        RestaurantManagementSystem restaurantManagementSystem = new RestaurantManagementSystem();
        restaurantManagementSystem.setReservationService(reservationService);
        restaurantManagementSystem.setOrderService(orderService);

//        Receptionist receptionist = new Receptionist("receptionist", branch);
//        Customer customer = new Customer("customer");
//
//        receptionist.setRestaurantManagementSystem(restaurantManagementSystem);
//        receptionist.setTableAssignmentStrategy(tableAssignmentStrategy);
//        customer.setRestaurantManagementSystem(restaurantManagementSystem);
//
//        // Reservation creation and cancellation flow testing:
//        customer.bookReservation(branch, LocalDateTime.of(2025, 9, 4, 7, 45, 0), 3);
//        customer.bookReservation(branch, LocalDateTime.of(2025, 9, 4, 7, 55, 0), 3);
//
//        Reservation res = reservationService.getUpcomingReservations().poll();
//        Reservation res1 = reservationService.getUpcomingReservations().stream().toList().get(1);
//
//        System.out.println(res.getId() + " " + res1.getId());
//        receptionist.assignTable(res);
//        customer.cancelReservation(res);
//        receptionist.assignTable(res1);

        MenuItem item = MenuItemFactory.createMenuItem("noodles", MenuSection.CHINESE, 10);
        Menu menu = new Menu();
        menu.addMenuItem(item);
        branch.setMenu(menu);
        Order order = orderService.createOrderForTable(table);
        OrderItem orderItem = new OrderItem(order, item);
        order.addOrderItem(orderItem);
        order.addOrderItem(orderItem);
        order.addOrderItem(orderItem);
        order.addOrderItem(orderItem);
        order.removeItem(orderItem);
        System.out.println(order.getOrderAmount());

    }
}