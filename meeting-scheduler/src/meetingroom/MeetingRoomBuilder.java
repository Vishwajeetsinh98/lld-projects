package meetingroom;

import calendar.Calendar;

public class MeetingRoomBuilder {
    private MeetingRoom meetingRoom;

    public MeetingRoomBuilder() {
        this.meetingRoom = new MeetingRoom();
    }

    public MeetingRoom createRoom() {
        meetingRoom.setCalendar(new Calendar());
        return meetingRoom;
    }

    public MeetingRoomBuilder withLocation(String location) {
        meetingRoom.setLocation(location);
        return this;
    }

    public MeetingRoomBuilder withCapacity(int capacity) {
        meetingRoom.setCapacity(capacity);
        return this;
    }

    public MeetingRoomBuilder reset() {
        meetingRoom = new MeetingRoom();
        return this;
    }
}
