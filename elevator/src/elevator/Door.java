package elevator;

import enums.DoorState;

public class Door {
    private DoorState state;

    public boolean isOpen() {
        return state == DoorState.OPEN;
    }

    public void open() {
        state = DoorState.OPEN;
    }

    public void close() {
        state = DoorState.CLOSED;
    }
}
