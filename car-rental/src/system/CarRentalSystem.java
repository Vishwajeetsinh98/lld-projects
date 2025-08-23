package system;

import reservations.ReservationService;
import vehicles.Vehicle;
import vehicles.factory.*;

public class CarRentalSystem {

    private final ReservationService reservationService;
    private final VehicleFactory carFactory;
    private final VehicleFactory vanFactory;
    private final VehicleFactory motorcycleFactory;
    private final VehicleFactory truckFactory;

    private CarRentalSystem() {
        reservationService = ReservationService.getInstance();
        carFactory = new CarFactory();
        vanFactory = new VanFactory();
        motorcycleFactory = new MotorcycleFactory();
        truckFactory = new TruckFactory();
    }

    public Vehicle createVehicle(String type, String subtype, String plate) {
        return switch (type.toLowerCase()) {
            case "car" -> carFactory.create(subtype, plate);
            case "truck" -> truckFactory.create(subtype, plate);
            case "motorcycle" -> motorcycleFactory.create(subtype, plate);
            case "van" -> vanFactory.create(subtype, plate);
            default -> throw new IllegalArgumentException("Invalid vehicle type: " + type);
        };
    }


    // Singleton:
    private static class Holder {
        private static final CarRentalSystem INSTANCE = new CarRentalSystem();
    }

    public static CarRentalSystem getInstance() {
        return Holder.INSTANCE;
    }
}
