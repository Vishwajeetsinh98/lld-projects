package order.shipment;

import notifications.NotificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Shipment {

    private static final AtomicLong id = new AtomicLong(1);

    private final String shipmentId;
    private ShipmentStatus shipmentStatus;
    private final String address;
    private final List<String> shipmentTracking;
    public Shipment(String address) {
        shipmentId = "S_" + id.getAndIncrement();
        this.address = address;
        shipmentStatus = ShipmentStatus.ON_TIME;
        shipmentTracking = new ArrayList<>();
    }

    public List<String> getShipmentTracking() {
        return shipmentTracking;
    }

    public void addTrackingEntry(String entry) {
        shipmentTracking.add(entry);
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Shipment to: " + address + "\n" + shipmentStatus;
    }
}
