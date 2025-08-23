package users;

import company.Branch;
import reservations.Reservation;
import reservations.ReservationService;
import vehicles.Vehicle;
import vehicles.VehicleService;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Customer extends User {
    public Customer(String id, String name) {
        super(id, name);
    }

    public void listAllVehicles() {
        System.out.println("[Customer] Listing all vehicles:");
        for (Vehicle vehicle : VehicleService.getInstance().getAllVehicles()) {
            System.out.println(vehicle);
        }
    }

    public void listByType(String type) {
        System.out.println("[Customer] Listing all " + type + "s");
        for (Vehicle vehicle : VehicleService.getInstance().getVehiclesByType(type)) {
            System.out.println(vehicle);
        }
    }

    public void listBySubtype(String type, String subtype) {
        System.out.println("[Customer] Listing all " + subtype + " " + type + "s");
        for (Vehicle vehicle : VehicleService.getInstance().getVehiclesBySubtype(type, subtype)) {
            System.out.println(vehicle);
        }
    }

    public Reservation reserveVehicle(String vehicleLicencePlate,
                                      LocalDateTime startDate, LocalDateTime endDate,
                                      Branch startBranch, Branch endBranch) {
        Vehicle vehicle = VehicleService.getInstance().getVehicle(vehicleLicencePlate);
        System.out.println(vehicle);
        Reservation reservation = ReservationService.getInstance().newReservation(id, vehicle,
                                                                                  new ArrayList<>(),
                                                                                  startDate, endDate,
                                                                                  startBranch, endBranch);

        ReservationService.getInstance().checkout(reservation, false);
        return reservation;
    }

    public void returnVehicle(Reservation reservation) {
        ReservationService.getInstance().complete(reservation, reservation.getEndBranch());
    }
}
