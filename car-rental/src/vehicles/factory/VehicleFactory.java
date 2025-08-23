package vehicles.factory;

import vehicles.Vehicle;

public interface VehicleFactory {
    public Vehicle create(String subtype, String licensePlate);
}
