package reservations;

import company.Branch;
import vehicles.Vehicle;
import vehicles.addons.AddOn;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationBuilder {
    private Reservation reservation;

    public ReservationBuilder() {
        reset();
    }

    public void reset() {
        reservation = new Reservation();
    }

    public Reservation build() throws IllegalArgumentException {
        reservation.validate();
        return reservation;
    }

    public ReservationBuilder withUserId(String userId) {
        reservation.setUserId(userId);
        return this;
    }

    public ReservationBuilder withVehicle(Vehicle vehicle) {
        reservation.setVehicle(vehicle);
        return this;
    }

    public ReservationBuilder withAddOn(AddOn addOn) {
        reservation.getAddOns().add(addOn);
        return this;
    }

    public ReservationBuilder withStartTime(LocalDateTime time) {
        reservation.setStartTime(time);
        return this;
    }

    public ReservationBuilder withEndTime(LocalDateTime time) {
        reservation.setEndTime(time);
        return this;
    }

    public ReservationBuilder withStartBranch(Branch branch) {
        reservation.setStartBranch(branch);
        reservation.setEndBranch(branch);
        return this;
    }

    public ReservationBuilder withEndBranch(Branch branch) {
        reservation.setEndBranch(branch);
        return this;
    }
}
