package vehicles.factory;

import vehicles.Vehicle;
import vehicles.VehicleService;
import vehicles.impl.EconomyCar;
import vehicles.impl.LuxuryCar;

public class CarFactory implements VehicleFactory {

    private static int ecId = 0;
    private static int lxId = 0;

    public Vehicle create(String subtype, String licensePlate) {
        Vehicle newVehicle = switch (subtype.toLowerCase()) {
            case "economy" -> new EconomyCar("car_economy_" + ++ecId, licensePlate);
            case "luxury" -> new LuxuryCar("car_luxury_" + ++lxId, licensePlate);
            default -> throw new IllegalArgumentException("Invalid car type: " + subtype);
        };
        VehicleService.getInstance().addVehicle("car", newVehicle);
        return newVehicle;
    }
}
