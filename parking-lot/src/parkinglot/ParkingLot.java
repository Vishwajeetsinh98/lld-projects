package parkinglot;

import enums.ParkingSpotType;
import parkinglot.parkingspot.ParkingSpot;
import parkinglot.parkingspot.ParkingSpotImpl;
import services.display.DisplayService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {

    private final int[] INITIAL_COUNTS = {10, 20, 10, 5};
    private final Map<String, ParkingSpot> parkingSpots;
    private final List<EntryGate> entryGates;
    private final List<ExitGate> exitGates;

    private ParkingLot() {
        parkingSpots = new HashMap<>();
        entryGates = new ArrayList<>();
        exitGates = new ArrayList<>();
        int id = 1;
        for (int type = 0;type < INITIAL_COUNTS.length;type++) {
            for (int i = 0;i < INITIAL_COUNTS[type];i++) {
                ParkingSpot spot = new ParkingSpotImpl("SPOT_"+id++, ParkingSpotType.values()[type]);
                parkingSpots.put(spot.getSpotId(), spot);
            }
        }
        addEntry();
        addExit();
    }

    public ParkingSpot getParkingSpot(String spotId) {
        return parkingSpots.get(spotId);
    }

    public List<ParkingSpot> getSpotsByType(ParkingSpotType parkingSpotType) {
        List<ParkingSpot> ret = new ArrayList<>();
        for (ParkingSpot parkingSpot : parkingSpots.values()) {
            if (parkingSpot.getType() == parkingSpotType)
                ret.add(parkingSpot);
        }
        return ret;
    }

    public int[] getInitialCounts() {
        return this.INITIAL_COUNTS;
    }

    public void addDisplayBoard() {
        DisplayService.getInstance().addNewBoard();
    }

    public void addEntry() {
        String id;
        if (entryGates.isEmpty()) {
            id = "ENTRY_1";
        } else {
            id = entryGates.getLast().getId().split("_")[1];
            id = Integer.toString(Integer.parseInt(id) + 1);
        }

        entryGates.add(new EntryGate("ENTRY_"+id));
    }
    public void removeEntry(String entryId) {
        entryGates.removeIf(e -> e.getId().equals(entryId));
    }

    public void addExit() {
        String id;
        if (exitGates.isEmpty()) {
            id = "EXIT_1";
        } else {
            id = exitGates.getLast().getId().split("_")[1];
            id = Integer.toString(Integer.parseInt("EXIT_"+id) + 1);
        }

        exitGates.add(new ExitGate(id));
    }

    public void removeExit(String exitId) {
        exitGates.removeIf(e -> e.getId().equals(exitId));
    }

    public List<EntryGate> getEntryGates() {
        return entryGates;
    }

    public List<ExitGate> getExitGates() {
        return exitGates;
    }


    // Singleton stuff:
    private static class Holder {
        private static final ParkingLot INSTANCE = new ParkingLot();
    }
    public static ParkingLot getInstance() {
        return Holder.INSTANCE;
    }
}
