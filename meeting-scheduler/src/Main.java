import meeting.Meeting;
import meeting.MeetingBuilder;
import meeting.MeetingRoomScheduler;
import meetingroom.MeetingRoom;
import meetingroom.MeetingRoomBuilder;
import users.InviteAcceptStatus;
import users.Organizer;
import users.Participant;
import utils.Interval;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        MeetingRoom meetingRoom = new MeetingRoomBuilder().withCapacity(5).withLocation("R1").createRoom();
        MeetingRoom meetingRoom1 = new MeetingRoomBuilder().withCapacity(10).withLocation("R2").createRoom();
        Participant p = new Participant("test");
        Organizer o = new Organizer("org");
        MeetingRoomScheduler scheduler = MeetingRoomScheduler.getInstance();
        scheduler.addMeetingRoom(meetingRoom);
        scheduler.addMeetingRoom(meetingRoom1);
        scheduler.addParticipant(p);
        scheduler.addParticipant(o);

        Interval interval = new Interval(LocalDateTime.of(2025, 8, 17, 8, 0, 0),
                                         LocalDateTime.of(2025, 8, 17, 9, 0, 0));

        Meeting meeting = o.createMeeting(interval.startTime(), interval.endTime(), meetingRoom.getId(), List.of(p.getId(), o.getId()));

        p.acceptInvite(meeting.getId());

        Interval newInterval = new Interval(LocalDateTime.of(2025, 8, 17, 10, 0, 0),
                LocalDateTime.of(2025, 8, 17, 11, 0, 0));

        o.updateMeetingTime(meeting.getId(), newInterval.startTime(), newInterval.endTime());

        p.acceptInvite(meeting.getId());

//        Meeting meeting1 = o.createMeeting(newInterval.startTime(), newInterval.endTime(), meetingRoom.getId(), List.of(p.getId()));
        Meeting meeting1 = o.createMeeting(interval.startTime(), interval.endTime(), meetingRoom.getId(), List.of(p.getId()));
        p.rejectInvite(meeting.getId());

    }
}
