package chatroom;

public interface Mediator {
    public void broadcast(User sender, String message);
}
