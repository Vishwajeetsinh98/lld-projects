package meeting;

import users.Organizer;
import users.Participant;
import utils.Interval;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Meeting {
    private static int autoIncId = 0;
    private final String id;
    private final List<String> participantIds;
    private String organizerId;
    private String meetingRoomId;
    private Interval interval;
    private MeetingStatus meetingStatus;

    public Meeting() {
        this.id = getNextId();
        participantIds = new ArrayList<>();
        meetingStatus = MeetingStatus.SCHEDULED;
    }

    public void addParticipant(String p) {
        if (!participantIds.contains(p))
            participantIds.add(p);
    }

    public void addParticipants(List<String> pList) {
        participantIds.addAll(pList);
    }

    public void removeParticipant(String p) {
        participantIds.remove(p);
    }

    public String getId() {
        return id;
    }

    public List<String> getParticipants() {
        return participantIds;
    }

    public String getOrganizer() {
        return organizerId;
    }

    public void setOrganizer(String organizerId) {
        this.organizerId = organizerId;
    }

    public String getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(String meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    public MeetingStatus getMeetingStatus() {
        return meetingStatus;
    }

    public void cancelMeeting() {
        meetingStatus = MeetingStatus.CANCELED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting m = (Meeting) o;
        return id.equals(m.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private static String getNextId() {
        return "M_" + ++autoIncId;
    }
}
