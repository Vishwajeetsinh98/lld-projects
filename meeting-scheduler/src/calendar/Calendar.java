package calendar;

import utils.Interval;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Calendar {
    private final Map<Interval, List<String>> timeToMeetingIdsMap;
    private final Map<String, Interval> meetingToTimeMap;

    public Calendar() {
        this.timeToMeetingIdsMap = new ConcurrentHashMap<>();
        this.meetingToTimeMap = new ConcurrentHashMap<>();
    }

    public boolean checkConflict(Interval interval) {
        return timeToMeetingIdsMap.keySet().stream()
                .anyMatch(existing -> existing.overlaps(interval));
    }

    public void addMeeting(Interval interval, String meetingId) {
        if (!timeToMeetingIdsMap.containsKey(interval))
            timeToMeetingIdsMap.put(interval, new ArrayList<>());
        if (!timeToMeetingIdsMap.get(interval).contains(meetingId)) {
            timeToMeetingIdsMap.get(interval).add(meetingId);
            meetingToTimeMap.put(meetingId, interval);
        }
        System.out.println("[Calendar] Added a " + interval.durationMinutes() + " minute meeting at: " + interval);
    }

    public void removeMeeting(String meetingId) {
        Interval interval = meetingToTimeMap.getOrDefault(meetingId, null);
        if (interval == null) {
            System.out.println("[Calendar] No meeting with id: " + meetingId + " scheduled at this time!");
            return;
        }
        meetingToTimeMap.remove(meetingId);
        timeToMeetingIdsMap.get(interval).remove(meetingId);
        if (timeToMeetingIdsMap.get(interval).isEmpty())
            timeToMeetingIdsMap.remove(interval);
        System.out.println("[Calendar] Removed a " + interval.durationMinutes() + " minute meeting at: " + interval);
    }
}
