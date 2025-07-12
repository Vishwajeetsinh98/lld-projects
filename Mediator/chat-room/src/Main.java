import chatroom.Room;
import chatroom.User;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");

        Room testRoom = new Room("test");
        user1.enterRoom(testRoom);
        user2.enterRoom(testRoom);
        user3.enterRoom(testRoom);

        user1.sendMessage("test", "This is a test message from 1");
        user2.sendMessage("test", "Hello world!");

    }
}
