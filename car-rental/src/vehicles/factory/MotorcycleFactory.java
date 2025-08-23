package vehicles.factory;

import vehicles.Vehicle;
import vehicles.VehicleService;
import vehicles.impl.CruiserMotorcycle;
import vehicles.impl.StandardMotorcycle;

public class MotorcycleFactory implements VehicleFactory {

    private static int standardId = 0;
    private static int cruiserId = 0;

    @Override
    public Vehicle create(String subtype, String licensePlate) {
        Vehicle newVehicle = switch (subtype.toLowerCase()) {
            case "standard" -> new StandardMotorcycle("motorcycle_standard_" + ++standardId, licensePlate);
            case "cruiser" -> new CruiserMotorcycle("motorcycle_cruiser_" + ++cruiserId, licensePlate);
            default -> throw new IllegalArgumentException("Invalid motorcycle type: " + subtype);
        };
        VehicleService.getInstance().addVehicle("motorcycle", newVehicle);
        return newVehicle;
    }
}
