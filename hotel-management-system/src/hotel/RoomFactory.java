package hotel;

public class RoomFactory {

    public static int[] room_ids = new int[]{1, 1, 1, 1};

    public static Room createRoom(RoomType type) {
        return switch (type) {
            case STANDARD -> new Room("S_"+room_ids[0]++, type, "Standard room with basic amenities.");
            case DELUXE -> new Room("D_"+room_ids[1]++, type, "Deluxe room with sauna.");
            case FAMILY_SUITE -> new Room("F_"+room_ids[2]++, type, "Family room with kitchen, living room.");
            case BUSINESS_SUITE -> new Room("B_"+room_ids[3]++, type, "Business room with meeting room and workspace.");
        };
    }
}
