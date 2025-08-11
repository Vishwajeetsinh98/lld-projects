import building.Building;
import system.ElevatorSystem;

public class Main {
    public static void main(String[] args) throws Exception {
        Building building = new Building(15);
        ElevatorSystem elevatorSystem = ElevatorSystem.getInstance();
//        building.getFloorsList().getFirst().getPanel().getUpButton().pressDown();
//        building.getElevatorCars().getFirst().getElevatorPanel().getFloorButton(5).pressDown();
//        elevatorSystem.updateDisplays();
        building.getFloorsList().get(1).getPanel().getUpButton().pressDown();
        elevatorSystem.dispatch();
        elevatorSystem.updateDisplays();
        elevatorSystem.getElevatorCars().get(0).getElevatorPanel().getFloorButton(15).pressDown();
        elevatorSystem.getElevatorCars().get(0).move();
    }
}
