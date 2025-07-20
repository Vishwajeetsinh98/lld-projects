package services.ticketing;

import enums.TicketStatus;
import services.payment.Payment;
import vehicles.Vehicle;

import java.time.LocalDateTime;

public class ParkingTicket {
    private final String ticketId;
    private final String spotId;
    private final LocalDateTime entryTime;
    private final Vehicle vehicle;
    private TicketStatus status;
    private LocalDateTime exitTime;
    private Payment payment;

    public ParkingTicket(String ticketId, String spotId, LocalDateTime entryTime, Vehicle vehicle) {
        this.ticketId = ticketId;
        this.spotId = spotId;
        this.entryTime = entryTime;
        this.vehicle = vehicle;
        this.status = TicketStatus.ISSUED;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getSpotId() {
        return spotId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

}