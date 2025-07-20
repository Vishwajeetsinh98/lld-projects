package services.payment;

import enums.ParkingSpotType;
import enums.PaymentStatus;
import enums.VehicleType;
import parkinglot.ParkingLot;
import parkinglot.parkingspot.ParkingSpot;
import services.ticketing.ParkingTicket;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class PaymentService {

    private static final Map<String, Payment> paymentMap = new HashMap<>();

    public Payment calculateAmount(ParkingTicket parkingTicket) {

        ParkingSpot spot = ParkingLot.getInstance().getParkingSpot(parkingTicket.getSpotId());

        double amount = switch(spot.getType()) {
            case ParkingSpotType.DISABLED -> 2;
            case ParkingSpotType.MOTOR_CYCLE -> 5;
            case ParkingSpotType.COMPACT -> 10;
            case ParkingSpotType.LARGE -> 20;
        };

        amount *= ChronoUnit.HOURS.between(parkingTicket.getEntryTime(), parkingTicket.getExitTime());

        Payment payment = PaymentFactory.createPayment(amount);
        paymentMap.put(payment.getId(), payment);
        parkingTicket.setPayment(payment);
        return payment;
    }

    public boolean processPayment(ParkingTicket parkingTicket, PaymentProcessor paymentProcessor) {
        boolean status = paymentProcessor.pay(parkingTicket.getPayment());
        if (status) {
            parkingTicket.getPayment().setPaymentStatus(PaymentStatus.PAID);
        } else {
            parkingTicket.getPayment().setPaymentStatus(PaymentStatus.CANCELLED);
        }

        return status;
    }

    public static Payment getPayment(String paymentId) {
        return paymentMap.get(paymentId);
    }


    // Singleton stuff:
    private static class Holder {
        private static final PaymentService INSTANCE = new PaymentService();
    }
    public static PaymentService getInstance() {
        return Holder.INSTANCE;
    }
}
