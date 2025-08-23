package vehicles.log;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VehicleLog {
    private final List<LogEntry> entries;

    public VehicleLog() {
        entries = new ArrayList<>();
    }

    public void addEntry(LocalDateTime date, String message, String author) {
        entries.add(new LogEntry(date, message, author));
    }

    public void showLog() {
        for (LogEntry entry : entries)
            System.out.println(entry);
    }
}
