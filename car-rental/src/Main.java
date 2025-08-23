import company.Branch;
import reservations.Reservation;
import system.CarRentalSystem;
import users.Customer;
import vehicles.Vehicle;
import vehicles.VehicleService;
import vehicles.factory.CarFactory;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("test", "test");
        Branch branch = new Branch();
        CarRentalSystem system = CarRentalSystem.getInstance();

        system.createVehicle("car", "economy", "1234");

        Reservation reservation = customer.reserveVehicle("1234", LocalDateTime.now(), LocalDateTime.of(2025, 8, 24, 5, 45), branch, branch);
        customer.returnVehicle(reservation);

    }
}