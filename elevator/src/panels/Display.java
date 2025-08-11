package panels;

import elevator.ElevatorCar;
import enums.ElevatorState;
import system.ElevatorSystem;

import java.util.HashMap;
import java.util.Map;

public class Display {
    private final boolean isElevatorDisplay;
    private final int elevatorId;

    private final Map<Integer, ElevatorStatus> elevatorStatusMap = new HashMap<>();

    public Display() {
        this.isElevatorDisplay = false;
        this.elevatorId = 0;
    }

    public Display(boolean isElevatorDisplay, int elevatorId) {
        this.isElevatorDisplay = isElevatorDisplay;
        this.elevatorId = elevatorId;
    }

    public void update() {
        for(ElevatorCar car : ElevatorSystem.getInstance().getElevatorCars()) {
            if (!isElevatorDisplay || elevatorId == car.getId()) {
                elevatorStatusMap.put(car.getId(), new ElevatorStatus(car.getCurrentFloor(),
                                                                      car.getState(),
                                                                      car.isOverload()));
            }
        }
        showDisplay();
    }

    public void showDisplay() {
        System.out.println("============================================================");
        System.out.println("ElevatorStatus");
        for (Map.Entry<Integer, ElevatorStatus> entry : elevatorStatusMap.entrySet()) {
            System.out.println("Car " + entry.getKey() + " -> floor: " + entry.getValue().floor + " state: " + entry.getValue().state + " overload: " + entry.getValue().isOverload);
        }
        System.out.println("============================================================");
    }

    private record ElevatorStatus(int floor, ElevatorState state, boolean isOverload) {}
}
