package system;

import building.Building;
import building.Floor;
import elevator.ElevatorCar;
import enums.Direction;
import enums.ElevatorState;
import panels.Display;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ElevatorSystem {

    private Building building;
    private List<ElevatorCar> elevatorCars;
    private List<Display> displays;
    private final Queue<FloorRequest> hallCallRequests;

    private ElevatorSystem() {
        hallCallRequests = new LinkedList<>();
    }

    public void callElevator(int floorNumber, Direction direction) {
        System.out.println("[ElevatorSystem] Received call: " + floorNumber + " to go: " + direction);
        hallCallRequests.add(new FloorRequest(floorNumber, direction));
    }

    public ElevatorCar getNearestIdleCar(int floor) {
        ElevatorCar best = null;
        int minDist = Integer.MAX_VALUE;
        for (ElevatorCar car : elevatorCars) {
            if (car.getState() == ElevatorState.IDLE &&
                    car.getState() != ElevatorState.MAINTENANCE &&
                    !car.isOverload()) {
                int dist = Math.abs(car.getCurrentFloor() - floor);
                if (dist < minDist) {
                    minDist = dist;
                    best = car;
                }
            }
        }
        return best;
    }

    public void dispatch() {
        while (!hallCallRequests.isEmpty()) {
            FloorRequest req = hallCallRequests.poll();
            ElevatorCar car = getNearestIdleCar(req.floorNumber);
            if (car == null) {
                System.out.println("[ElevatorSystem] No idle car available; re-queueing request");
                hallCallRequests.offer(req);
                break;
            }
            System.out.printf("[ElevatorSystem] Dispatching Elevator %d to floor %d%n", car.getId()+1, req.floorNumber);
            car.registerRequest(req.floorNumber);
            car.move();
        }
    }

    public void attachBuilding(Building building) {
        this.building = building;
        this.elevatorCars = building.getElevatorCars();
    }

    public List<ElevatorCar> getElevatorCars() {
        return elevatorCars;
    }

    public Building getBuilding() {
        return building;
    }

    public void collectDisplays() {
        displays = new ArrayList<>();
        for (Floor floor : building.getFloorsList()) {
            displays.add(floor.getPanel().getDisplay());
        }
        for (ElevatorCar car : elevatorCars) {
            displays.add(car.getElevatorPanel().getDisplay());
        }
    }

    public void updateDisplays() {
        for (Display display : displays)
            display.update();
    }

    // Singleton:
    private static final class Holder {
        private static final ElevatorSystem INSTANCE = new ElevatorSystem();
    }
    public static ElevatorSystem getInstance() {
        return Holder.INSTANCE;
    }

    private record FloorRequest(int floorNumber, Direction direction){}

}
