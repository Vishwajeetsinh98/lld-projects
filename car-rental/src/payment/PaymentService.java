package payment;

import reservations.Reservation;
import vehicles.addons.AddOn;

import java.time.temporal.ChronoUnit;

public class PaymentService {

    public double calculateFees(Reservation reservation) {
        double amount = 0.0;

        long hours = ChronoUnit.HOURS.between(reservation.getStartTime(), reservation.getEndTime());

        double rate = 0.0;

        if (reservation.getVehicle().getId().startsWith("car")) {
            rate = 10.0;
        } else if (reservation.getVehicle().getId().startsWith("motorcycle")) {
            rate = 5.0;
        } else if (reservation.getVehicle().getId().startsWith("van")) {
            rate = 15.0;
        } else if (reservation.getVehicle().getId().startsWith("truck")) {
            rate = 20.0;
        } else {
            System.out.println("[PaymentService] Unknown vehicle type: " + reservation.getVehicle().getId());
        }

        amount = hours * rate;

        System.out.println("[PaymentService] amount for " + hours + " at " + rate + " per hour: " + amount);

        for (AddOn addOn : reservation.getAddOns()) {
            amount += addOn.getCost() * hours / 8.0;
        }

        System.out.println("[PaymentService] Amount after addons: " + amount);

        if (reservation.getEndTime().isAfter(reservation.getReturnTime())) {
            // No late fees
            System.out.println("[PaymentService] No late fees!");
        } else {
            hours = ChronoUnit.HOURS.between(reservation.getEndTime(), reservation.getReturnTime());
            System.out.println("[PaymentService] Reservation was: " + hours + " late. Double rate will be applied.");
            amount += hours * rate * 2.0;
            for (AddOn addOn : reservation.getAddOns()) {
                amount += addOn.getCost() * 2.0 * hours / 8.0;
            }
        }

        return amount;
    }

    public boolean collectFees(double amount, String method) {
        PaymentStrategy paymentStrategy;
        paymentStrategy = switch (method.toLowerCase()) {
            case "cash" -> new CashPaymentStrategy(amount, 10000);
            case "card" -> new CardPaymentStrategy("1234567890", "test", amount);
            default -> throw new IllegalStateException("Unexpected payment method: " + method.toLowerCase());
        };
        return paymentStrategy.collect();
    }

    // Singleton:
    private static class Holder {
        private static final PaymentService INSTANCE = new PaymentService();
    }

    public static PaymentService getInstance() {
        return Holder.INSTANCE;
    }
}
