import enums.VehicleType;
import parkinglot.EntryGate;
import parkinglot.ExitGate;
import parkinglot.ParkingLot;

public class Main {
    public static void main(String[] args) {

        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.addDisplayBoard();
        EntryGate entryGate = parkingLot.getEntryGates().getFirst();
        ExitGate exitGate = parkingLot.getExitGates().getFirst();

        entryGate.enterVehicle("TEST1", VehicleType.CAR, false);
        entryGate.enterVehicle("TEST2", VehicleType.CAR, true);
        entryGate.enterVehicle("TEST3", VehicleType.MOTOR_CYCLE, false);
        entryGate.enterVehicle("TEST4", VehicleType.TRUCK, false);

        exitGate.exitVehicle("CAR_0");
        exitGate.exitVehicle("CAR_1");

    }
}
