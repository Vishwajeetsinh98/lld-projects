package reservations;

import company.Branch;
import vehicles.Vehicle;
import vehicles.addons.AddOn;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Reservation {

    private static final AtomicLong currentId = new AtomicLong(0);

    private final String id;
    private String userId;
    private Vehicle vehicle;
    private List<AddOn> addOns;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime returnTime;
    private Branch startBranch;
    private Branch endBranch;
    private ReservationStatus reservationStatus;
    private boolean paid;

    public Reservation() {
        id = "RES_" + currentId.incrementAndGet();
        userId = null;
        vehicle = null;
        addOns = new ArrayList<>();
        startTime = null;
        endTime = null;
        returnTime = null;
        startBranch = null;
        endBranch = null;
        reservationStatus = ReservationStatus.RESERVED;
        paid = false;
    }

    public void validate() {
        if (userId == null)
            throw new IllegalArgumentException("User not set");
        if (vehicle == null)
            throw new IllegalArgumentException("Vehicle not set");
        if (startTime == null)
            throw new IllegalArgumentException("Start time not set");
        if (endTime == null)
            throw new IllegalArgumentException("End time not set");
        if (startTime.isAfter(endTime) || startTime.equals(endTime))
            throw new IllegalArgumentException("Start time cannot be after end time");
        if (startBranch == null || endBranch == null)
            throw new IllegalArgumentException("Start or end branch not set");
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<AddOn> getAddOns() {
        return addOns;
    }

    public void setAddOns(List<AddOn> addOns) {
        this.addOns = addOns;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime time) {
        returnTime = time;
    }

    public Branch getStartBranch() {
        return startBranch;
    }

    public void setStartBranch(Branch startBranch) {
        this.startBranch = startBranch;
    }

    public Branch getEndBranch() {
        return endBranch;
    }

    public void setEndBranch(Branch endBranch) {
        this.endBranch = endBranch;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
