package users;

import calendar.Calendar;
import meeting.Meeting;
import meeting.MeetingRoomScheduler;
import meeting.MeetingStatus;
import utils.Interval;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Participant {

    private static int autoIncId = 0;

    protected Calendar calendar;
    protected final String id;
    protected String name;
    protected final Map<String, InviteAcceptStatus> meetingInviteMap;
    protected final MeetingRoomScheduler meetingRoomScheduler;

    public Participant(String name) {
        this.id = getNextId();
        this.name = name;
        this.meetingInviteMap = new ConcurrentHashMap<>();
        this.calendar = new Calendar();
        meetingRoomScheduler = MeetingRoomScheduler.getInstance();
    }

    public void getNotification(String meetingId, MeetingStatus status) {
        Meeting meeting = meetingRoomScheduler.getMeeting(meetingId);
        if (meeting == null) {
            System.out.println("[Participant] Invalid meeting: " + meetingId);
            return;
        }
        if (!meetingInviteMap.containsKey(meetingId) &&
                status != MeetingStatus.SCHEDULED) {
            System.out.println("[Participant] " + id + " got notification for an unknown meeting: " + meetingId);
            return;
        }

        System.out.println("[Participant] " + id + " got a notification for " + meetingId + " with status: " + status);
        switch (status) {
            case SCHEDULED:
                // Schedule meeting
                if (meetingInviteMap.containsKey(meetingId) && meetingInviteMap.get(meetingId) != InviteAcceptStatus.PENDING) {
                    System.out.println("[Participant] " + id + " already replied to this meeting invite.");
                } else {
                    meetingInviteMap.put(meetingId, InviteAcceptStatus.PENDING);
                }
                break;
            case CANCELED:
                if (meetingInviteMap.get(meetingId) == InviteAcceptStatus.ACCEPTED)
                    calendar.removeMeeting(meetingId);
                meetingInviteMap.remove(meetingId);
                break;
            case UPDATED:
                calendar.removeMeeting(meetingId);
                meetingInviteMap.put(meetingId, InviteAcceptStatus.PENDING);
                break;
        }
    }

    public void acceptInvite(String meetingId) {
        Meeting meeting = meetingRoomScheduler.getMeeting(meetingId);
        Interval interval = meeting.getInterval();
        if (calendar.checkConflict(interval))
            throw new RuntimeException("Participant calendar booked at this time");
        calendar.addMeeting(interval, meetingId);
        meetingInviteMap.put(meetingId, InviteAcceptStatus.ACCEPTED);
    }

    public void rejectInvite(String meetingId) {
        calendar.removeMeeting(meetingId);
        meetingInviteMap.put(meetingId, InviteAcceptStatus.REJECTED);
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public Calendar getCalendar() {return calendar;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant p = (Participant) o;
        return id.equals(p.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private String getNextId() {
        return "P_" + ++autoIncId;
    }
}
