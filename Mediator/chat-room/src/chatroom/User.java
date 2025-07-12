package chatroom;

import java.util.HashMap;
import java.util.Map;

public class User {
    private final String id;
    private final Map<String, Room> roomsMap;

    public User(String id) {
        this.id = id;
        this.roomsMap = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void enterRoom(Room room) {
        roomsMap.put(room.getName(), room);
        room.addMember(this);
    }

    public void leaveRoom(String roomName) {
        if (!roomsMap.containsKey(roomName))
            return;
        roomsMap.get(roomName).removeMember(this);
        roomsMap.remove(roomName);
    }

    public void sendMessage(String roomName, String message) {
        if (!roomsMap.containsKey(roomName))
            return;
        roomsMap.get(roomName).broadcast(this, message);
    }

    public void receiveMessage(String from, String message) {
        System.out.println("User: " + id + " received message:\nFrom: " + from + ": " + message);
        System.out.println("===============================================");
    }
}
