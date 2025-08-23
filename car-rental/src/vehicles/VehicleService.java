package vehicles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VehicleService {

    private List<Vehicle> cars;
    private List<Vehicle> vans;
    private List<Vehicle> motorcycles;
    private List<Vehicle> trucks;
    private Map<String, Vehicle> allVehicles;

    private VehicleService() {
        cars = new ArrayList<>();
        vans = new ArrayList<>();
        motorcycles = new ArrayList<>();
        trucks = new ArrayList<>();
        allVehicles = new ConcurrentHashMap<>();
    }

    private List<Vehicle> getCollection(String type) {
        return switch (type.toLowerCase()) {
            case "car" -> cars;
            case "van" -> vans;
            case "motorcycle" -> motorcycles;
            case "truck" -> trucks;
            default -> throw new IllegalArgumentException("Illegal type: " + type);
        };
    }

    public void addVehicle(String type, Vehicle vehicle) {
        List<Vehicle> collection = getCollection(type);
        if (!collection.contains(vehicle)) {
            System.out.println("[VehicleService] adding vehicle: " + vehicle.getLicensePlate());
            collection.add(vehicle);
            allVehicles.put(vehicle.getLicensePlate(), vehicle);
        }
    }

    public Vehicle getVehicle(String plate) {
        return allVehicles.getOrDefault(plate, null);
    }

    public List<Vehicle> getVehiclesByType(String type) {
        return getCollection(type);
    }

    public List<Vehicle> getVehiclesBySubtype(String type, String subtype) {
        List<Vehicle> collection = getCollection(type);
        return collection.stream().filter(e -> e.getId().split("_")[1].toLowerCase().equals(subtype)).toList();
    }

    public List<Vehicle> getAllVehicles() {
        return allVehicles.values().stream().toList();
    }

    // Singleton:
    private static class Holder {
        private static final VehicleService INSTANCE = new VehicleService();
    }

    public static VehicleService getInstance() {
        return Holder.INSTANCE;
    }
}
