package chatroom;

import java.util.ArrayList;
import java.util.List;

public class Room implements Mediator {

    private final String name;
    private final List<User> members;

    public Room(String name) {
        this.name = name;
        members = new ArrayList<>();
    }

    public void addMember(User user) {
        members.add(user);
    }

    public void removeMember(User user) {
        members.remove(user);
    }

    public String getName() {return this.name;}

    @Override
    public void broadcast(User sender, String message) {
        for (User member : members) {
            if (!member.equals(sender))
                member.receiveMessage(sender.getId(), message);
        }
    }
}
