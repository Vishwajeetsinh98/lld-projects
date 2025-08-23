package vehicles;

import vehicles.log.VehicleLog;

import java.util.Objects;

public abstract class Vehicle {
    protected final String id;
    protected final String licensePlate;
    protected VehicleStatus vehicleStatus;
    protected final VehicleLog vehicleLog;

    public Vehicle(String id, String licensePlate) {
        this.id = id;
        this.licensePlate = licensePlate;
        vehicleLog = new VehicleLog();
    }

    public String getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public VehicleLog getVehicleLog() {
        return vehicleLog;
    }

    @Override
    public String toString() {
        return id.split("_")[1].toUpperCase() + " " + id.split("_")[0].toUpperCase() + ", plate: " + licensePlate;
    }

    @Override
    public boolean equals(Object v) {
        if (this == v) return true;
        if (!(v instanceof Vehicle)) return false;
        return licensePlate.equals(((Vehicle) v).getLicensePlate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
