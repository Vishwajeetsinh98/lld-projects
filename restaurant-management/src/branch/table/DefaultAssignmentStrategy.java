package branch.table;

import branch.Branch;
import reservation.Reservation;

import java.util.List;

public class DefaultAssignmentStrategy implements TableAssignmentStrategy {

    private Branch branch;
    public DefaultAssignmentStrategy() {}

    public void setBranch(Branch branch) { this.branch = branch; }

    @Override
    public Table assign(Reservation reservation) {
        List<Table> tables = branch.getTables();
        // Find first table without any reservations.
        for (Table table : tables) {
            if (table.getOccupancy() >= reservation.getNumPeople()) {
                if (table.getReservations().isEmpty())
                    return table;
            }
        }
        // If all tables have reservations - find the first which doesn't conflict.
        for (Table table : tables) {
            if (table.getOccupancy() >= reservation.getNumPeople()) {
                List<Reservation> reservations = table.getReservations();
                boolean conflict = false;
                for (Reservation tableReservation : reservations) {
                    // Assume average 1 hour appointment per table.
                    // TODO: Add more checks here for conflict
                    if (tableReservation.getStartTime().isBefore(reservation.getStartTime()) &&
                            tableReservation.getStartTime().plusHours(1).isAfter(reservation.getStartTime())) {
                        conflict = true;
                        break;
                    }
                }
                if (!conflict)
                    return table;
            }
        }

        // No tables found!
        return null;
    }
}
