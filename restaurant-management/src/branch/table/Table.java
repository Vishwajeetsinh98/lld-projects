package branch.table;

import branch.staff.Server;
import customer.Customer;
import reservation.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Table {
    private static final AtomicLong idCounter = new AtomicLong(1);
    private final long id;
    private final List<Reservation> reservations;
    private Server server;
    private boolean isOccupied;
    private Customer customer;
    private int occupancy;

    public Table() {
        id = idCounter.getAndIncrement();
        reservations = new ArrayList<>();
        occupancy = 4;
    }

    public Table(int occupancy) {
        this();
        this.occupancy = occupancy;
    }

    public void occupyTable(Customer customer) {
        System.out.println("[Table] " + id + " occupying.");
        setCustomer(customer);
        setOccupied(true);
        customer.setSeated(true);
        customer.setServer(server);
    }

    public void vacateTable() {
        System.out.println("[Table] " + id + " vacating.");
        customer.setSeated(false);
        customer.setServer(null);
        setCustomer(null);
        setOccupied(false);
    }

    public void addReservation(Reservation reservation) {
        if (!reservations.contains(reservation)) {
            System.out.println("[Table] " + id +
                                    " added new reservation at: " + reservation.getStartTime());
            reservations.add(reservation);
        }
    }

    public void removeReservation(Reservation reservation) {
        if (!reservations.contains(reservation)) return;
        System.out.println("[Table] " + id +
                                " removed reservation at: " + reservation.getStartTime());
        reservations.remove(reservation);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Table)) return false;
        return ((Table) o).id == id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void clearReservations() { reservations.clear(); }

    public List<Reservation> getReservations() { return reservations; }

    public long getId() { return id; }

    public Server getServer() { return server; }

    public void setServer(Server server) { this.server = server; }

    public boolean isOccupied() { return isOccupied; }

    public void setOccupied(boolean occupied) { isOccupied = occupied; }

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public int getOccupancy() { return occupancy; }
}
