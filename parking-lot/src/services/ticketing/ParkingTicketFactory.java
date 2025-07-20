package services.ticketing;


import enums.VehicleType;
import parkinglot.parkingspot.ParkingSpot;
import vehicles.Vehicle;

import java.time.LocalDateTime;

public class ParkingTicketFactory {

    private static final int[] ids = new int[VehicleType.values().length];

    public static ParkingTicket createTicket(Vehicle vehicle, ParkingSpot spot) {
        String ticketId = vehicle.getType().toString() + "_" + ids[vehicle.getType().ordinal()]++;
//        return new ParkingTicket(ticketId, spot.getSpotId(), LocalDateTime.now(), vehicle);
        return new ParkingTicket(ticketId, spot.getSpotId(), LocalDateTime.of(2025, 7, 19, 6, 0), vehicle);

    }
}
