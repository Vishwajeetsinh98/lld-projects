package meeting;

import meetingroom.MeetingRoom;
import meetingroom.MeetingRoomBuilder;
import users.Participant;
import utils.Interval;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MeetingRoomScheduler {

    private final Map<String, Participant> participantMap;
    private final Map<String, MeetingRoom> meetingRoomMap;
    private final Map<String, Meeting> meetingMap;

    private MeetingRoomScheduler() {
        participantMap = new ConcurrentHashMap<>();
        meetingRoomMap = new ConcurrentHashMap<>();
        meetingMap = new ConcurrentHashMap<>();
    }


    public void scheduleMeeting(Meeting meeting, String organizerId) {
        // Check meeting room capacity
        MeetingRoom meetingRoom = meetingRoomMap.getOrDefault(meeting.getMeetingRoomId(),
                                                    null);
        if (meetingRoom == null)
            throw new RuntimeException("Invalid Meeting Room provided");

        if (!meeting.getOrganizer().equals(organizerId))
            throw new RuntimeException("Only organizer can schedule meeting");

        if (meetingRoom.getCapacity() < meeting.getParticipants().size())
            throw new RuntimeException("Meeting room " + meetingRoom.getCapacity() + " won't fit " + meeting.getParticipants().size());

        // Check meeting room calendar
        if (meetingRoom.getCalendar().checkConflict(meeting.getInterval()))
            throw new RuntimeException("Meeting room has a conflict");

        meetingRoom.getCalendar().addMeeting(meeting.getInterval(), meeting.getId());

        // add to meetingMap
        addMeeting(meeting);

        // Notify participants
        notifyParticipants(meeting.getParticipants(),
                            meeting.getId(),
                            MeetingStatus.SCHEDULED);
    }

    public void cancelMeeting(String meetingId, String organizerId) {
        Meeting meeting = meetingMap.getOrDefault(meetingId, null);
        if (meeting == null)
            throw new RuntimeException("Invalid Meeting id: " + meetingId);

        if (!meeting.getOrganizer().equals(organizerId))
            throw new RuntimeException("Only organizer can cancel meeting");

        // Remove from MR calendar
        MeetingRoom meetingRoom = meetingRoomMap.getOrDefault(meeting.getMeetingRoomId(), null);
        if (meetingRoom == null)
            throw new RuntimeException("Invalid meeting room id " + meeting.getMeetingRoomId());

        meetingRoom.getCalendar().removeMeeting(meetingId);

        // Send notification to participants
        notifyParticipants(meeting.getParticipants(),
                            meetingId,
                            MeetingStatus.CANCELED);
    }

    public void updateMeetingTime(String meetingId, Interval newInterval, String organizerId) {
        Meeting meeting = meetingMap.getOrDefault(meetingId, null);
        if (meeting == null)
            throw new RuntimeException("Invalid Meeting id: " + meetingId);

        if (!meeting.getOrganizer().equals(organizerId))
            throw new RuntimeException("Only organizer can update meeting time");

        if (meeting.getMeetingStatus() != MeetingStatus.SCHEDULED &&
                meeting.getMeetingStatus() != MeetingStatus.UPDATED)
            throw new RuntimeException("Cannot update an unscheduled meeting");

        // Check MR calendar
        MeetingRoom meetingRoom = meetingRoomMap.getOrDefault(meeting.getMeetingRoomId(),
                null);
        if (meetingRoom == null)
            throw new RuntimeException("Invalid Meeting Room provided");

        // Check meeting room calendar
        if (meetingRoom.getCalendar().checkConflict(newInterval))
            throw new RuntimeException("Meeting room has a conflict");

        // Update meeting timings
        meeting.setInterval(newInterval);
        meetingRoom.getCalendar().removeMeeting(meetingId);
        meetingRoom.getCalendar().addMeeting(meeting.getInterval(), meetingId);
        System.out.println("[MeetingRoomScheduler] Meeting time for " + meetingId + " updated to: " + newInterval);

        // Notify participants
        notifyParticipants(meeting.getParticipants(), meetingId, MeetingStatus.UPDATED);
    }

    public void updateMeetingRoom(String meetingId, String newRoomId, String organizerId) {
        Meeting meeting = getMeeting(meetingId);
        if (meeting == null)
            throw new RuntimeException("Invalid meeting id: " + meetingId);

        if (!meeting.getOrganizer().equals(organizerId))
            throw new RuntimeException("Only organizer can update meeting room");

        MeetingRoom newRoom = getMeetingRoom(newRoomId);
        if (newRoom == null)
            throw new RuntimeException("Invalid meeting room: " + newRoomId);

        // Check for capacity
        if (newRoom.getCapacity() < meeting.getParticipants().size())
            throw new RuntimeException("New room doesn't fit all participants");

        // Check new room calendar
        if (newRoom.getCalendar().checkConflict(meeting.getInterval()))
            throw new RuntimeException("Meeting room has a conflict for the meeting time");

        System.out.println("[MeetingRoomScheduler] Meeting room for " + meetingId + " changed to: " + newRoomId);
        meeting.setMeetingRoomId(newRoomId);
    }

    public void addMeetingParticipant(String meetingId, String participantId, String organizerId) {
        Meeting meeting = getMeeting(meetingId);
        Participant participant = getParticipant(participantId);
        if (meeting == null)
            throw new RuntimeException("Invalid meeting id: " + meetingId);

        if (!meeting.getOrganizer().equals(organizerId))
            throw new RuntimeException("Only organizer can add meeting participants");

        if (participant == null)
            throw new RuntimeException("Invalid participant id: " + participantId);

        if (getMeetingRoom(meeting.getMeetingRoomId()).getCapacity() == meeting.getParticipants().size())
            throw new RuntimeException("Cannot add participant to meeting as the room is full");

        System.out.println("[MeetingRoomScheduler] Adding participant " + participantId + " to: " + meetingId);
        meeting.addParticipant(participantId);
        notifyParticipants(List.of(participantId), meetingId, MeetingStatus.SCHEDULED);
    }

    public void removeMeetingParticipant(String meetingId, String participantId, String organizerId) {
        Meeting meeting = getMeeting(meetingId);
        Participant participant = getParticipant(participantId);
        if (meeting == null)
            throw new RuntimeException("Invalid meeting id: " + meetingId);

        if (!meeting.getOrganizer().equals(organizerId))
            throw new RuntimeException("Only organizer can remove meeting participants");

        if (participant == null)
            throw new RuntimeException("Invalid participant id: " + participantId);

        if (!meeting.getParticipants().contains(participantId))
            return;

        System.out.println("[MeetingRoomScheduler] Removing participant " + participantId + " from: " + meetingId);
        meeting.removeParticipant(participantId);
        notifyParticipants(List.of(participantId), meetingId, MeetingStatus.CANCELED);
    }

    public void notifyParticipants(List<String> pList, String meetingId, MeetingStatus meetingStatus) {
        System.out.println("[MeetingRoomScheduler] Notifying " + pList.size() + " participants about: " + meetingId + " status: " + meetingStatus);
        for (String pId : pList) {
            Participant p = participantMap.getOrDefault(pId, null);
            if (p == null) {
                System.out.println("[MeetingRoomScheduler] invalid participant id: " + pId);
                continue;
            }
            p.getNotification(meetingId, meetingStatus);
        }
    }

    public void addParticipant(Participant p) {
        participantMap.put(p.getId(), p);
    }

    public void addMeetingRoom(MeetingRoom mr) {
        meetingRoomMap.put(mr.getId(), mr);
    }

    private void addMeeting(Meeting m) {
        meetingMap.put(m.getId(), m);
    }

    public Participant getParticipant(String id) {
        return participantMap.getOrDefault(id, null);
    }

    public MeetingRoom getMeetingRoom(String id) {
        return meetingRoomMap.getOrDefault(id, null);
    }

    public Meeting getMeeting(String id) {
        return meetingMap.getOrDefault(id, null);
    }

    // Singleton:
    private static class Holder {
        private static final MeetingRoomScheduler INSTANCE = new MeetingRoomScheduler();
    }

    public static MeetingRoomScheduler getInstance() {
        return Holder.INSTANCE;
    }
}
