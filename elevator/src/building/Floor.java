package building;

import panels.HallPanel;

public class Floor {
    private final int floorNumber;
    private final HallPanel panel;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        panel = new HallPanel(this);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public HallPanel getPanel() {
        return panel;
    }
}
