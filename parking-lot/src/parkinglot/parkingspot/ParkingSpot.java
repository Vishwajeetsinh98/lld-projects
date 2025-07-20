package parkinglot.parkingspot;

import enums.ParkingSpotType;
import vehicles.Vehicle;

public interface ParkingSpot {
    public String getSpotId();
    public ParkingSpotType getType();
    public boolean isOccupied();
    public void assignVehicle(Vehicle vehicle);
    public void removeVehicle();
    public Vehicle getVehicle();
}
