package buttons;

import enums.Direction;
import system.ElevatorSystem;

public class HallButton extends Button {
    private final Direction direction;
    private final int floorNumber;
    public HallButton(Direction direction, int floorNumber) {
        super();
        this.direction = direction;
        this.floorNumber = floorNumber;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void pressDown() {
        System.out.println("[HallButton] " + direction + " pressed");
        super.pressDown();
        // Call elevator
        ElevatorSystem.getInstance().callElevator(floorNumber, direction);
    }
}
