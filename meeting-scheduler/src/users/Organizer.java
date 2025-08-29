package users;

import meeting.Meeting;
import meeting.MeetingBuilder;
import meetingroom.MeetingRoom;
import utils.Interval;

import java.time.LocalDateTime;
import java.util.List;

public class Organizer extends Participant {

    public Organizer(String name) {
        super(name);
    }

    public Meeting createMeeting(LocalDateTime startTime, LocalDateTime endTime,
                              String meetingRoomId, List<String> participants) {
        Interval interval = new Interval(startTime, endTime);
        MeetingRoom meetingRoom = meetingRoomScheduler.getMeetingRoom(meetingRoomId);
        if (meetingRoom == null)
            throw new RuntimeException("Invalid meeting room id: " + meetingRoomId);

        Meeting meeting = new MeetingBuilder().withMeetingRoom(meetingRoomId).withInterval(interval).withOrganizer(id).withParticipants(participants).build();
        meetingRoomScheduler.scheduleMeeting(meeting, id);
        return meeting;
    }

    public void addMeetingParticipant(String meetingId, String participantId) {
        meetingRoomScheduler.addMeetingParticipant(meetingId, participantId, id);
    }

    public void removeMeetingParticipant(String meetingId, String participantId) {
        meetingRoomScheduler.removeMeetingParticipant(meetingId, participantId, id);
    }

    public void updateMeetingTime(String meetingId, LocalDateTime start, LocalDateTime end) {
        Interval newInterval = new Interval(start, end);
        meetingRoomScheduler.updateMeetingTime(meetingId, newInterval, id);
    }

    public void updateMeetingRoom(String meetingId, String meetingRoomId) {
        meetingRoomScheduler.updateMeetingRoom(meetingId, meetingRoomId, id);
    }
}
