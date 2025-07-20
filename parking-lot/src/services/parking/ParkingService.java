package services.parking;

import parkinglot.ParkingLot;
import parkinglot.parkingspot.ParkingSpot;
import services.display.DisplayService;
import vehicles.Vehicle;

public class ParkingService {

    private SpotAssignmentStrategy assignmentStrategy;

    private ParkingService(SpotAssignmentStrategy assignmentStrategy) {
        this.assignmentStrategy = assignmentStrategy;
    }

    public ParkingSpot assignSpot(Vehicle vehicle) {
        ParkingSpot spot = assignmentStrategy.assign(vehicle);
        if (spot == null) {
            throw new RuntimeException("Parking full!");
        }
        spot.assignVehicle(vehicle);
        DisplayService.getInstance().updateDisplays(spot.getType(), -1);
        return spot;
    }

    public void releaseSpot(String spotId) {
        ParkingSpot spot = ParkingLot.getInstance().getParkingSpot(spotId);
        DisplayService.getInstance().updateDisplays(spot.getType(), 1);
        spot.removeVehicle();
    }

    public void changeSpotAssignmentStrategy(SpotAssignmentStrategy assignmentStrategy) {
        this.assignmentStrategy = assignmentStrategy;
    }

    // Singleton stuff
    private static class Holder {
        private static final ParkingService INSTANCE = new ParkingService(new DefaultSpotAssignmentStrategy());
    }
    public static ParkingService getInstance() {
        return Holder.INSTANCE;
    }
}
