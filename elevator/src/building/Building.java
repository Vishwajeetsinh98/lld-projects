package building;

import elevator.ElevatorCar;
import system.ElevatorSystem;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private final int floors;
    private final List<Floor> floorsList;
    private final List<ElevatorCar> elevatorCars;

    public Building(int floors) {
        this.floors = floors;
        floorsList = new ArrayList<>();
        elevatorCars = new ArrayList<>();
        ElevatorSystem.getInstance().attachBuilding(this);

        for (int i = 0;i < floors;i++) {
            floorsList.add(new Floor(i));
        }
        for (int i = 0;i < 3;i++) {
            elevatorCars.add(new ElevatorCar(i + 1));
        }
        ElevatorSystem.getInstance().collectDisplays();
    }

    public int getFloors() {
        return floors;
    }

    public List<Floor> getFloorsList() {
        return floorsList;
    }

    public List<ElevatorCar> getElevatorCars() {
        return elevatorCars;
    }
}
