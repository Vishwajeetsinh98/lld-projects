package meetingroom;

import calendar.Calendar;

public class MeetingRoom {

    private static int autoIncId = 0;

    private final String id;
    private int capacity;
    private String location;
    private Calendar calendar;

    public MeetingRoom() {
        id = getNextId();
    }

    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    void setLocation(String location) {
        this.location = location;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    private static String getNextId() {
        return "CR_" + ++autoIncId;
    }
}
