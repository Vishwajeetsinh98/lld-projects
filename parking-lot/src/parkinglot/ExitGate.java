package parkinglot;

import enums.PaymentMethod;
import services.parking.ParkingService;
import services.payment.Payment;
import services.payment.PaymentService;
import services.ticketing.ParkingTicket;
import services.ticketing.TicketingService;
import vehicles.VehicleFactory;

import java.time.LocalDateTime;
import java.util.Random;

public class ExitGate {
    private final String id;

    public ExitGate(String id) {
        this.id = id;
    }

    public String getId() {return this.id;}

    public void exitVehicle(String ticketId) {
        ParkingTicket parkingTicket = TicketingService.getInstance().getTicket(ticketId);
        parkingTicket.setExitTime(LocalDateTime.now());

        Payment payment = PaymentService.getInstance().calculateAmount(parkingTicket);
        System.out.println("[Amount to be paid]: " + payment.getAmount() + ". Cash or card?");
        int random = new Random().nextInt(0, 2);
        payment.setPaymentMethod(random == 0 ? PaymentMethod.CARD : PaymentMethod.CASH);
        System.out.println("Paying by " + payment.getPaymentMethod());
        boolean status = TicketingService.getInstance().closeTicket(parkingTicket.getTicketId());
        if (status) {
            System.out.println("[Exit Gate " + id + "]: Vehicle " + parkingTicket.getVehicle().getLicensePlate() + " exiting, after paying " + parkingTicket.getPayment().getAmount());
            ParkingService.getInstance().releaseSpot(parkingTicket.getSpotId());
            System.out.println("[Exit Gate " + id + "]: Validating ticket.");

            VehicleFactory.removeVehicle(parkingTicket.getVehicle());

        } else {
            System.out.println("ERROR!! [Exit Gate " + id + "]: Vehicle " + parkingTicket.getVehicle().getLicensePlate() + " payment failed..");
        }
    }
}
