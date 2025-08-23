package vehicles.factory;

import vehicles.Vehicle;
import vehicles.VehicleService;
import vehicles.impl.CargoVan;
import vehicles.impl.PassengerVan;

public class VanFactory implements VehicleFactory {

    private static int passengerId = 0;
    private static int cargoId = 0;

    @Override
    public Vehicle create(String subtype, String licensePlate) {
        Vehicle newVehicle = switch(subtype.toLowerCase()) {
            case "passenger" -> new PassengerVan("van_passenger_" + ++passengerId, licensePlate);
            case "cargo" -> new CargoVan("van_cargo_" + ++cargoId, licensePlate);
            default -> throw new IllegalArgumentException("Invalid van type: " + subtype);
        };
        VehicleService.getInstance().addVehicle("van", newVehicle);
        return newVehicle;
    }
}
