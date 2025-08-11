package buttons;

import enums.DoorState;

public class DoorButton extends Button {
    private final DoorState state;
    public DoorButton(DoorState state) {
        this.state = state;
    }

    public DoorState getState() {
        return state;
    }

    @Override
    public void pressDown() {
        System.out.println("[DoorButton] " + state + " pressed");
        super.pressDown();
        // Open / Close door
    }
}
