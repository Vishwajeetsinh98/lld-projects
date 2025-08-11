package panels;

import buttons.Button;
import buttons.DoorButton;
import buttons.ElevatorButton;
import buttons.EmergencyButton;
import enums.DoorState;
import system.ElevatorSystem;

import java.util.ArrayList;
import java.util.List;

public class ElevatorPanel {
    private final List<Button> elevatorButtons;
    private final Button doorOpenButton;
    private final Button doorCloseButton;
    private final Button emergencyButton;
    private final Display display;
    private final int elevatorCarId;

    public ElevatorPanel(int elevatorCarId) {
        this.elevatorCarId = elevatorCarId;
        this.elevatorButtons = new ArrayList<>();
        for (int i = 0;i < ElevatorSystem.getInstance().getBuilding().getFloors() + 1;i++) {
            this.elevatorButtons.add(new ElevatorButton(i, elevatorCarId));
        }
        this.doorOpenButton = new DoorButton(DoorState.OPEN);
        this.doorCloseButton = new DoorButton(DoorState.CLOSED);
        this.emergencyButton = new EmergencyButton();
        this.display = new Display();
    }

    public Button getFloorButton(int floor) {
        return this.elevatorButtons.get(floor);
    }

    public Button getDoorOpenButton() {
        return doorOpenButton;
    }

    public Button getDoorCloseButton() {
        return doorCloseButton;
    }

    public Button getEmergencyButton() {
        return emergencyButton;
    }

    public Display getDisplay() {
        return display;
    }
}
