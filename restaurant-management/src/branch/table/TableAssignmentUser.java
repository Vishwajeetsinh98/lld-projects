package branch.table;

import reservation.Reservation;

public interface TableAssignmentUser {
    public void assignTable(Reservation reservation);
    public void assignSpecificTable(Reservation reservation, Table table);
}
