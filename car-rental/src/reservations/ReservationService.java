package reservations;

import company.Branch;
import payment.PaymentService;
import vehicles.Vehicle;
import vehicles.addons.AddOn;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ReservationService {
    private final ReservationBuilder builder;
    private final Map<String, List<Reservation>> reservationsByUser;

    private ReservationService() {
        builder = new ReservationBuilder();
        reservationsByUser = new ConcurrentHashMap<>();
    }

    public Reservation newReservation(String userId, Vehicle vehicle,
                                      List<AddOn> addOns,
                                      LocalDateTime startTime, LocalDateTime endTime,
                                      Branch startBranch, Branch endBranch) {
        builder.reset();
        builder.withUserId(userId).withVehicle(vehicle)
                .withStartTime(startTime).withEndTime(endTime)
                .withStartBranch(startBranch).withEndBranch(endBranch);
        addOns.forEach(builder::withAddOn);
        Reservation ret = builder.build();
        System.out.println("[ReservationService] Created new reservation for " + userId);
        reservationsByUser.computeIfAbsent(userId, k -> new ArrayList<>());
        reservationsByUser.get(userId).add(ret);
        return ret;
    }

    public void checkout(Reservation reservation, boolean shouldPayNow) {
        reservation.validate();
        System.out.println("[ReservationService] checking out reservation: " + reservation.getId());
        if (shouldPayNow) {
            // Payment
            System.out.println("[ReservationService] collecting payment now");
        }
        reservation.setReservationStatus(ReservationStatus.ACTIVE);
    }

    public void complete(Reservation reservation, Branch branch) {
        System.out.println("[ReservationService] completing reservation: " + reservation.getId());
        boolean branchMismatch = false;
        if (reservation.getEndBranch() != branch) {
            System.out.println("[ReservationService] End branch was supposed to be: " + reservation.getEndBranch() + ". You are at: " + branch + ". Will add extra $ to payment.");
            branchMismatch = true;
        }
        reservation.setReturnTime(LocalDateTime.now());

        double fees = PaymentService.getInstance().calculateFees(reservation);
        System.out.println("[ReservationService] Fees calculated: " + fees);
        boolean status = PaymentService.getInstance().collectFees(fees, "cash");
        if (status) {
            reservation.setPaid(true);
            System.out.println("[ReservationService] Reservation successfully completed!");
            reservation.setReservationStatus(ReservationStatus.COMPLETED);
        } else {
            System.out.println("[ReservationService] Error while completing reservation!");
        }
    }

    public void extend(Reservation reservation, LocalDateTime newEndTime) {
        if (reservation.getStartTime().isAfter(newEndTime))
            throw new IllegalArgumentException("Reservation end time cannot be before start time");

        System.out.println("[ReservationService] Extending reservation" + reservation.getId() + " to: " + newEndTime);
        reservation.setEndTime(newEndTime);
    }

    public void changeEndBranch(Reservation reservation, Branch endBranch) {
        if (endBranch == null)
            throw new IllegalArgumentException("End branch cannot be null");
        System.out.println("[ReservationService] Changing end branch to: " + endBranch);
        reservation.setEndBranch(endBranch);
    }

    public void cancel(Reservation reservation) {
        if (reservation.getReservationStatus() != ReservationStatus.RESERVED)
            throw new IllegalArgumentException("Can not cancel an active/completed reservation");
        System.out.println("[ReservationService] Setting reservation status for " + reservation.getId() + " to canceled");
        reservation.setReservationStatus(ReservationStatus.CANCELED);
    }

    // Singleton
    private static class Holder {
        private static final ReservationService INSTANCE = new ReservationService();
    }

    public static ReservationService getInstance() {
        return Holder.INSTANCE;
    }
}
