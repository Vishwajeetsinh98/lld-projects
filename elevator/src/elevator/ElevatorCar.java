package elevator;

import enums.ElevatorState;
import panels.ElevatorPanel;
import system.ElevatorSystem;

import java.util.LinkedList;
import java.util.Queue;

public class ElevatorCar {

    private final int id;
    private final double MAX_LOAD = 680;
    private int currentFloor;
    private ElevatorState state;
    private final Queue<Integer> requestQueue;
    private double load;
    private boolean overload;

    private final ElevatorPanel elevatorPanel;
    private final Door door;

    public ElevatorCar(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.state = ElevatorState.IDLE;
        this.requestQueue = new LinkedList<>();
        this.load = 0.0;
        this.overload = false;
        this.elevatorPanel = new ElevatorPanel(id);
        this.door = new Door();
    }

    public void registerRequest(int floor) {
        System.out.println("[ElevatorCar] " + id + " registered request to move to: " + floor);
        requestQueue.add(floor);
    }

    public void stop() {
        this.door.open();
        this.state = ElevatorState.IDLE;
    }

    public void move() {
        System.out.println("[ElevatorCar] " + id + " " + state + " moving " + requestQueue.size());
        if (requestQueue.isEmpty() ||
                state == ElevatorState.MAINTENANCE ||
                state == ElevatorState.EMERGENCY) {
            return;
        }

        int target = requestQueue.poll();
        System.out.println("[ElevatorCar] " + id + " moving to: " + target);
        if (target == currentFloor) {
            stop();
            return;
        }

        state = target > currentFloor ? ElevatorState.MOVING_UP : ElevatorState.MOVING_DOWN;
        while (currentFloor != target && state != ElevatorState.MAINTENANCE && !isOverload()) {
            currentFloor += (state == ElevatorState.MOVING_UP ? 1 : -1);
            System.out.println("[ElevatorCar] " + id + " passing from " + currentFloor + " to reach " + target);
        }
        stop();
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        if (state == ElevatorState.MAINTENANCE) {
            setMaintenance();
        }
        this.state = state;
    }

    public void setMaintenance() {
        this.requestQueue.clear();
    }

    public Queue<Integer> getRequestQueue() {
        return requestQueue;
    }

    public double getLoad() {
        return load;
    }

    public void addLoad(double load) {
        this.load += load;
        if (this.load > MAX_LOAD) {
            this.overload = true;
        }
    }

    public void removeLoad(double load) {
        this.load -= load;
    }

    public boolean isOverload() {
        return overload;
    }

    public void setOverload(boolean overload) {
        this.overload = overload;
    }

    public ElevatorPanel getElevatorPanel() {
        return elevatorPanel;
    }

    public Door getDoor() {
        return door;
    }
}
