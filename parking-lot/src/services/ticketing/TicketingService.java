package services.ticketing;

import enums.PaymentMethod;
import enums.TicketStatus;
import parkinglot.parkingspot.ParkingSpot;
import services.payment.CardPaymentProcessor;
import services.payment.CashPaymentProcessor;
import services.payment.PaymentProcessor;
import services.payment.PaymentService;
import vehicles.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class TicketingService {
    private final Map<String, ParkingTicket> tickets;

    private TicketingService() {
        tickets = new HashMap<>();
    }

    public ParkingTicket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
        ParkingTicket ticket = ParkingTicketFactory.createTicket(vehicle, parkingSpot);
        tickets.put(ticket.getTicketId(), ticket);
        System.out.println("[TicketingService]: generated ticket " + ticket.getTicketId());
        return ticket;
    }

    public ParkingTicket getTicket(String ticketId) {
        return tickets.get(ticketId);
    }

    public boolean closeTicket(String ticketId) {
        ParkingTicket parkingTicket = tickets.get(ticketId);
        PaymentProcessor paymentProcessor = switch(parkingTicket.getPayment().getPaymentMethod()) {
            case PaymentMethod.CARD -> new CardPaymentProcessor("1234456789012", "1234", parkingTicket.getPayment().getAmount());
            case PaymentMethod.CASH -> new CashPaymentProcessor(parkingTicket.getPayment().getAmount(), parkingTicket.getPayment().getAmount() + 6.5);
        };
        boolean status = PaymentService.getInstance().processPayment(parkingTicket, paymentProcessor);

        if(status) {
            parkingTicket.setStatus(TicketStatus.PAID);
        } else {
            parkingTicket.setStatus(TicketStatus.CANCELLED);
        }

        return status;
    }

    // Singleton stuff:
    private static class Holder {
        private static final TicketingService INSTANCE = new TicketingService();
    }
    public static TicketingService getInstance() {
        return Holder.INSTANCE;
    }
}
