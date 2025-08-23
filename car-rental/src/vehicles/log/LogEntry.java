package vehicles.log;

import java.time.LocalDateTime;

public record LogEntry(LocalDateTime entryDate, String message, String author) {
    public String toString() {
        return "[" + entryDate.toString() + " - " + author + "]: " + message;
    }
}
