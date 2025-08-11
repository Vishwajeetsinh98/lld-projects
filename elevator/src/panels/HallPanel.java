package panels;

import building.Floor;
import buttons.Button;
import buttons.HallButton;
import enums.Direction;

public class HallPanel {

    private final Floor floor;
    private final Button upButton;
    private final Button downButton;
    private final Display display;
    public HallPanel(Floor floor) {
        this.floor = floor;
        this.upButton = new HallButton(Direction.UP, floor.getFloorNumber());
        this.downButton = new HallButton(Direction.DOWN, floor.getFloorNumber());
        this.display = new Display();
    }

    public Floor getFloor() {
        return floor;
    }

    public Button getUpButton() {
        return upButton;
    }

    public Button getDownButton() {
        return downButton;
    }

    public Display getDisplay() {
        return display;
    }
}
