package meeting;

import users.Organizer;
import users.Participant;
import utils.Interval;

import java.util.List;

public class MeetingBuilder {
    private Meeting meeting;

    public MeetingBuilder() {
        this.meeting = new Meeting();
    }

    public MeetingBuilder withOrganizer(String o) {
        meeting.setOrganizer(o);
        return this;
    }

    public MeetingBuilder withParticipant(String p) {
        meeting.addParticipant(p);
        return this;
    }

    public MeetingBuilder withParticipants(List<String> pList) {
        meeting.addParticipants(pList);
        return this;
    }

    public MeetingBuilder withInterval(Interval interval) {
        meeting.setInterval(interval);
        return this;
    }

    public MeetingBuilder withMeetingRoom(String meetingRoomId) {
        meeting.setMeetingRoomId(meetingRoomId);
        return this;
    }

    public Meeting build() {
        return meeting;
    }

    public MeetingBuilder reset() {
        meeting = new Meeting();
        return this;
    }
}
