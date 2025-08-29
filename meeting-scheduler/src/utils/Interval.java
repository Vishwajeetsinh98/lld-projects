package utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public record Interval(LocalDateTime startTime, LocalDateTime endTime) {

    public Interval {
        if (!startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("startTime must be before endTime");
        }
    }

    public boolean overlaps(Interval other) {
        return this.startTime.isBefore(other.endTime) &&
                other.startTime.isBefore(this.endTime);
    }

    public long durationMinutes() {
        return Duration.between(startTime, endTime).toMinutes();
    }

    public String toString() {
        return startTime.toString() + " - " + endTime.toString();
    }
}
