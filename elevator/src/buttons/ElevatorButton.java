package buttons;

import system.ElevatorSystem;

public class ElevatorButton extends Button {
    private final int destinationFloor;
    private final int elevatorCarId;
    public ElevatorButton(int destinationFloor, int elevatorCarId) {
        super();
        this.destinationFloor = destinationFloor;
        this.elevatorCarId = elevatorCarId;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    @Override
    public void pressDown() {
        System.out.println("[ElevatorButton] " + destinationFloor + " pressed");
        super.pressDown();
        // Add request to floor.
        ElevatorSystem.getInstance().getElevatorCars().get(elevatorCarId - 1).registerRequest(destinationFloor);
    }

}
