package reservation;

import branch.Branch;
import branch.table.Table;
import customer.Customer;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Reservation {

    private static final AtomicLong idCounter = new AtomicLong(1);
    private final String id;
    private final Branch branch;
    private final LocalDateTime startTime;
    private final Customer customer;
    private Table table;
    private ReservationState reservationState;
    private final int numPeople;

    public Reservation(Branch branch, LocalDateTime startTime, Customer customer, int numPeople) {
        id = "RES_" + idCounter.getAndIncrement();
        this.branch = branch;
        this.startTime = startTime;
        this.customer = customer;
        this.table = null;
        this.numPeople = numPeople;
        reservationState = ReservationState.RESERVED;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Reservation)) return false;
        return ((Reservation) o).id.equals(id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    public String getId() { return id; }

    public Branch getBranch() { return branch; }

    public LocalDateTime getStartTime() { return startTime; }

    public Customer getCustomer() { return customer; }

    public Table getTable() { return table; }

    public void setTable(Table table) { this.table = table; }

    public ReservationState getReservationState() { return reservationState; }

    public void setReservationState(ReservationState reservationState) { this.reservationState = reservationState; }

    public int getNumPeople() { return numPeople; }
}
