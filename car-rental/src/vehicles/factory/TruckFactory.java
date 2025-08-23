package vehicles.factory;

import vehicles.Vehicle;
import vehicles.VehicleService;
import vehicles.impl.HeavyDutyTruck;
import vehicles.impl.LightDutyTruck;
import vehicles.impl.MediumDutyTruck;

public class TruckFactory implements VehicleFactory {

    private static int lightId = 0;
    private static int mediumId = 0;
    private static int heavyId = 0;

    @Override
    public Vehicle create(String subtype, String licensePlate) {
        Vehicle newVehicle = switch(subtype.toLowerCase()) {
            case "light" -> new LightDutyTruck("truck_lightduty_" + ++lightId, licensePlate);
            case "medium" -> new MediumDutyTruck("truck_mediumduty_" + ++lightId, licensePlate);
            case "heavy" -> new HeavyDutyTruck("truck_heavyduty_" + ++lightId, licensePlate);
            default -> throw new IllegalArgumentException("Invalid truck type: " + subtype);
        };
        VehicleService.getInstance().addVehicle("truck", newVehicle);
        return newVehicle;

    }
}
