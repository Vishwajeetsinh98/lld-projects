package parkinglot;

import enums.VehicleType;
import parkinglot.parkingspot.ParkingSpot;
import services.parking.ParkingService;
import services.ticketing.ParkingTicket;
import services.ticketing.TicketingService;
import vehicles.Vehicle;
import vehicles.VehicleFactory;

public class EntryGate {
    private final String id;

    public EntryGate(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void enterVehicle(String licensePlate, VehicleType type, boolean isDisabled) {
        Vehicle vehicle;
        try {
            vehicle = VehicleFactory.createVehicle(licensePlate, type, isDisabled);
        } catch (RuntimeException e) {
            System.out.println("Vehicle already exists in the database!");
            return;
        }
        ParkingSpot parkingSpot = ParkingService.getInstance().assignSpot(vehicle);
        // Ticket is saved in the function to allow for ticket printer to print the ticket (not implemented)
        ParkingTicket parkingTicket = TicketingService.getInstance().generateTicket(vehicle, parkingSpot);
        System.out.println("[Entry gate " + id + "]: Parking ticket generated: " + parkingTicket.getTicketId() + " time: " + parkingTicket.getEntryTime());
        System.out.println("[Entry gate " + id + "]: Vehicle: " + vehicle.getLicensePlate() + " allotted spot: " + parkingSpot.getSpotId());
    }
}
