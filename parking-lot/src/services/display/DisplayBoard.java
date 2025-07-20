package services.display;

import enums.ParkingSpotType;

public class DisplayBoard {

    private final String id;
    public DisplayBoard(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void update() {
        System.out.println("XXXXXXXXXXXX [DisplayBoard " + id + "]: XXXXXXXXXXXX");
        for (ParkingSpotType type : ParkingSpotType.values()) {
            int count = DisplayService.getInstance().getCount(type);
            System.out.println(type + ": " + (count == 0 ? "FULL!" : count));
        }
        System.out.println("=================================================");
    }
}
