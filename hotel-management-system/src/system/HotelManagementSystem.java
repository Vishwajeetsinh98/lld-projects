package system;

import booking.Booking;
import booking.BookingBuilder;
import booking.BookingStatus;
import booking.Reservation;
import hotel.*;
import notification.NotificationService;
import payment.CardPaymentStrategy;
import payment.CashPaymentStrategy;
import payment.PaymentService;
import system.roomstrategy.DefaultRoomAssignmentStrategy;
import system.roomstrategy.RoomAssignmentStrategy;
import users.Guest;
import users.HouseKeeping;
import users.User;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HotelManagementSystem {
    private final Map<String, Branch> branchMap;
    private final Map<String, User> userMap;
    private final BookingBuilder bookingBuilder;
    private RoomAssignmentStrategy roomAssignmentStrategy;
    private PaymentService paymentService;
    private final List<Booking> bookings;
    private final NotificationService notificationService;

    public HotelManagementSystem() {
        branchMap = new ConcurrentHashMap<>();
        userMap = new ConcurrentHashMap<>();
        bookingBuilder = new BookingBuilder();
        roomAssignmentStrategy = new DefaultRoomAssignmentStrategy();
        paymentService = new PaymentService();
        bookings = new ArrayList<>();
        notificationService = new NotificationService();
    }

    public Booking createBooking(Guest guest, LocalDate start, LocalDate end, Branch branch, RoomType type) {
        return createBooking(guest, start, end, branch, type, null);
    }

    public Booking createBooking(Guest guest, LocalDate start, LocalDate end, Branch branch, RoomType type, String requests) {
        if (!branchMap.containsValue(branch))
            throw new IllegalArgumentException("Branch doesn't exist!");

        bookingBuilder.reset();
        Reservation reservation = new Reservation(start.atTime(14, 0, 0), end.atTime(12, 0, 0));
        Room room = roomAssignmentStrategy.assign(this, branch, type, reservation);
        if (room == null)
            throw new IllegalArgumentException("Rooms of type " + type + " booked for given dates!");
        Booking booking = bookingBuilder.withGuest(guest).withReservation(reservation).withRoom(room).withRequests(requests).build();
        bookings.add(booking);
        double bookingAmount = calculatePayment(type, reservation);
        System.out.println("[HotelManagementSystem] Created booking, amount: " + bookingAmount + ". Calculating 50% advance payment");
        booking.setBookingAmount(bookingAmount);
        paymentService.setPaymentStrategy(new CardPaymentStrategy());
        if (!paymentService.collect(bookingAmount * 0.5, new String[] {"card", "1234"})) {
            throw new UnsupportedOperationException("Payment failed!");
        }
        booking.setPaidSoFar(bookingAmount * 0.5);
        notificationService.sendNotification(guest.getUserName(), "Booking successful for dates: " + start + " - " + end + " at: " + branch.getBranchId());
        return booking;
    }

    public void checkIn(Booking booking) {
        System.out.println("[HotelManagementSystem] Checking in booking.");
        if (booking.getStatus() != BookingStatus.RESERVED)
            throw new IllegalArgumentException("Can only check-in non-active bookings, not: " + booking.getStatus());
//        if (LocalDate.now().isBefore(booking.getReservation().startTime().toLocalDate()))
//            throw new IllegalArgumentException("Cannot check in before check-in date.");

        System.out.println("[HotelManagementSystem] need to collect remainder payment at check in");
        if (booking.getPaidSoFar() != booking.getBookingAmount()) {
            paymentService.setPaymentStrategy(new CashPaymentStrategy());
            if (!paymentService.collect(booking.getBookingAmount() - booking.getPaidSoFar(),
                                     booking.getBookingAmount() - booking.getPaidSoFar() + 1))
                throw new IllegalArgumentException("Payment failed!");
        }

        booking.getRoom().checkInRoom(booking.getReservation());
        Key key = new Key(false);
        key.activateKey(booking.getRoom().getRoomNumber(), booking.getReservation());
        booking.setStatus(BookingStatus.ACTIVE);
        System.out.println("[HotelManagementSystem] Key card generated!");
        System.out.println("[HotelManagementSystem] check in successful!");
        notificationService.sendNotification(booking.getGuest().getUserName(), "You have checked in to your booking at room: " + booking.getRoom().getRoomNumber());
    }

    public void checkOut(Booking booking) {
        System.out.println("[HotelManagementSystem] Checking out booking.");
        if (booking.getStatus() != BookingStatus.ACTIVE)
            throw new IllegalArgumentException("Can only checkout ongoing bookings");

        booking.getRoom().checkOutRoom();
        booking.setStatus(BookingStatus.COMPLETED);
        System.out.println("[HotelManagementSystem] checked out room.");
        notificationService.sendNotification(booking.getGuest().getUserName(), "You have checked out of your booking at room: " + booking.getRoom().getRoomNumber());
    }

    public void cancelBooking(Booking booking) {
        System.out.println("[HotelManagementSystem] Canceling booking.");

        if (booking.getStatus() != BookingStatus.RESERVED)
            throw new IllegalArgumentException("Can only cancel upcoming reservations, not already canceled, or past ones.");

        // Check date of check-in after > 24 hours - refund. Else, just cancel.
        if (ChronoUnit.HOURS.between(LocalDateTime.now(), booking.getReservation().startTime()) > 24) {
            System.out.println("[HotelManagementSystem] Returning " + booking.getPaidSoFar());
        }

        // Remove reservation from Room.
        booking.getRoom().removeReservation(booking.getReservation());
        booking.setStatus(BookingStatus.CANCELED);
        System.out.println("[HotelManagementSystem] Booking canceled!");
        notificationService.sendNotification(booking.getGuest().getUserName(), "Your booking is canceled for date: " + booking.getReservation().startTime());
    }

    public void createCleanupLog(Branch branch, String roomNumber, HouseKeeping cleaner) {
        System.out.println("[HotelManagementSystem] Adding cleanup log entry for " + branch.getBranchId() + " room: " + roomNumber);
        branch.addCleanupLogEntry(new CleanupLog(roomNumber, LocalDateTime.now(), cleaner));
    }

    // TODO: Create BookingService class that take care of this logic.
    // We can also include discounts in it - controlled by Manager.
    private double calculatePayment(RoomType type, Reservation reservation) {
        double rate = switch (type) {
            case STANDARD -> 50.0;
            case DELUXE -> 75.0;
            case FAMILY_SUITE -> 120;
            case BUSINESS_SUITE -> 150;
        };

        long nights = ChronoUnit.DAYS.between(reservation.startTime().toLocalDate(),
                                        reservation.endTime().toLocalDate());
        return rate * nights;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setPaymentService(PaymentService service) {
        paymentService = service;
    }

    public void setRoomAssignmentStrategy(RoomAssignmentStrategy strategy) {
        roomAssignmentStrategy = strategy;
    }

    public Branch getBranch(String branchId) {
        return branchMap.getOrDefault(branchId, null);
    }

    public User getUser(String userName) {
        return userMap.getOrDefault(userName, null);
    }

    public void addBranch(Branch branch) {
        if (!branchMap.containsKey(branch.getBranchId()))
            branchMap.put(branch.getBranchId(), branch);
    }

    public void addUser(User user) {
        if (!userMap.containsKey(user.getUserName()))
            userMap.put(user.getUserName(), user);
    }

    private static final class Holder {
        private static final HotelManagementSystem INSTANCE = new HotelManagementSystem();
    }

    public static HotelManagementSystem getInstance() {
        return Holder.INSTANCE;
    }
}
