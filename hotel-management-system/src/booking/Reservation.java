package booking;

import java.time.LocalDateTime;
import java.util.Objects;

public record Reservation(LocalDateTime startTime, LocalDateTime endTime) {
    public Reservation(LocalDateTime startTime, LocalDateTime endTime) {
        if (endTime.isBefore(startTime))
            throw new IllegalArgumentException("Start time cannot be after end time");
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean hasConflict(Reservation other) {
        // No conflict if one interval ends before the other begins, or vice versa
        // Otherwise, there is an overlap/conflict
        return !this.endTime.isBefore(other.startTime) && !other.endTime.isBefore(this.startTime);
    }

    public boolean contains(Reservation other) {
        if (startTime.isAfter(other.startTime) || startTime.isEqual(other.endTime))
            return endTime.isBefore(other.endTime) || endTime.isEqual(other.endTime);
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Reservation)) return false;
        return ((Reservation) o).startTime.isEqual(startTime) &&
                    ((Reservation) o).endTime.isEqual(endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }
}
