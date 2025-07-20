package vehicles;

import enums.VehicleType;

public abstract class Vehicle {
    private final String licensePlate;
    private final VehicleType type;
    private final boolean isDisabled;

    public Vehicle(String licensePlate, VehicleType type, boolean isDisabled) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.isDisabled = isDisabled;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getType() {
        return type;
    }

    public boolean isDisabled () {
        return isDisabled;
    }
}
