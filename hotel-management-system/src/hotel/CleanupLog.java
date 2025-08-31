package hotel;

import users.HouseKeeping;

import java.time.LocalDateTime;

public class CleanupLog {
    private final String roomNumber;
    private final LocalDateTime time;
    private final HouseKeeping cleaner;

    public CleanupLog(String roomNumber, LocalDateTime time, HouseKeeping cleaner) {
        this.roomNumber = roomNumber;
        this.time = time;
        this.cleaner = cleaner;
    }

    @Override
    public String toString() {
        return "[CleanupLog] Room: " + roomNumber + " was cleaned by: " + cleaner.getUserName() + " at: " + time;
    }
}
