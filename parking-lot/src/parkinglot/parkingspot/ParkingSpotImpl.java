package parkinglot.parkingspot;

import enums.ParkingSpotType;
import vehicles.Vehicle;

public class ParkingSpotImpl implements ParkingSpot {

    private final String id;
    private final ParkingSpotType type;
    private boolean isOccupied;
    private Vehicle vehicle;

    public ParkingSpotImpl(String id, ParkingSpotType type) {
        this.id = id;
        this.type = type;
        this.isOccupied = false;
        this.vehicle = null;
    }

    @Override
    public String getSpotId() {
        return this.id;
    }

    @Override
    public ParkingSpotType getType() {
        return this.type;
    }

    @Override
    public boolean isOccupied() {
        return this.isOccupied;
    }

    @Override
    public void assignVehicle(Vehicle vehicle) {
        if (isOccupied) {
            throw new RuntimeException("Spot already in use!");
        }
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    @Override
    public void removeVehicle() {
        if (isOccupied) {
            this.isOccupied = false;
            this.vehicle = null;
        }
    }

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }
}
