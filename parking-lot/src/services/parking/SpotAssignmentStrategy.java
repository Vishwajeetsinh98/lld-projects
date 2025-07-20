package services.parking;

import parkinglot.parkingspot.ParkingSpot;
import vehicles.Vehicle;

public interface SpotAssignmentStrategy {
    ParkingSpot assign(Vehicle vehicle);
}
