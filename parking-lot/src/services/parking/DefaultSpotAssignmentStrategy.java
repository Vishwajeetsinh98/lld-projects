package services.parking;

import enums.ParkingSpotType;
import parkinglot.ParkingLot;
import parkinglot.parkingspot.ParkingSpot;
import vehicles.Vehicle;

import java.util.List;

public class DefaultSpotAssignmentStrategy implements SpotAssignmentStrategy {

    @Override
    public ParkingSpot assign(Vehicle vehicle) {
        ParkingSpotType parkingSpotType = ParkingSpotType.COMPACT;
        if (vehicle.isDisabled())
            parkingSpotType = ParkingSpotType.DISABLED;
        else {
            switch (vehicle.getType()) {
                case TRUCK -> parkingSpotType = ParkingSpotType.LARGE;
                case MOTOR_CYCLE -> parkingSpotType = ParkingSpotType.MOTOR_CYCLE;
            }
        }

        List<ParkingSpot> parkingSpots = ParkingLot.getInstance().getSpotsByType(parkingSpotType);
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (!parkingSpot.isOccupied())
                return parkingSpot;
        }
        return null;
    }
}
