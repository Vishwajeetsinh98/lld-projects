package branch.table;

import branch.Branch;
import reservation.Reservation;

public interface TableAssignmentStrategy {
    public Table assign(Reservation reservation);
    public void setBranch(Branch branch);
}
