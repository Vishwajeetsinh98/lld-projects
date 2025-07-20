package vehicles;

import enums.VehicleType;

import java.util.HashSet;
import java.util.Set;

public class VehicleFactory {

    private static final Set<String> licensePlates = new HashSet<>();

    public static Vehicle createVehicle(String licensePlate, VehicleType type, boolean isDisabled) {
        if (licensePlates.contains(licensePlate))
            throw new RuntimeException("A vehicle already exists in database with this license plate number!");
        licensePlates.add(licensePlate);
        switch (type) {
            case CAR -> {
                return new Car(licensePlate, VehicleType.CAR, isDisabled);
            }
            case MOTOR_CYCLE -> {
                return new MotorCycle(licensePlate, VehicleType.MOTOR_CYCLE, isDisabled);
            }
            case TRUCK -> {
                return new Truck(licensePlate, VehicleType.TRUCK, isDisabled);
            }
            case VAN -> {
                return new Van(licensePlate, VehicleType.VAN, isDisabled);
            }
        }
        return null;
    }

    public static void removeVehicle(Vehicle vehicle) {
        licensePlates.remove(vehicle.getLicensePlate());
    }
}