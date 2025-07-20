package services.display;

import enums.ParkingSpotType;
import parkinglot.ParkingLot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayService {

    private final List<DisplayBoard> displayBoards;
    private final Map<ParkingSpotType, Integer> counts;

    private DisplayService(int [] initialCounts) {
        this.displayBoards = new ArrayList<>();
        this.counts = new HashMap<>();
        for (ParkingSpotType type : ParkingSpotType.values()) {
            counts.put(type, initialCounts[type.ordinal()]);
        }
    }

    public void updateDisplays(ParkingSpotType vehicleType, int increment) {
        counts.put(vehicleType, counts.get(vehicleType) + increment);
        for (DisplayBoard displayBoard : displayBoards) {
            displayBoard.update();
        }
    }

    public int getCount(ParkingSpotType type) {
        return counts.get(type);
    }

    public void addNewBoard() {
        String id;
        if (displayBoards.isEmpty()) {
            id = "BOARD_1";
        } else {
            id = displayBoards.getLast().getId().split("_")[1];
            id = Integer.toString(Integer.parseInt(id) + 1);
        }

        displayBoards.add(new DisplayBoard("BOARD_"+id));
    }

    public void addBoard(DisplayBoard board) {
        displayBoards.add(board);
    }

    public void removeBoard(DisplayBoard board) {
        displayBoards.remove(board);
    }


    private static class Holder {
        private static final DisplayService INSTANCE = new DisplayService(ParkingLot.getInstance().getInitialCounts());
    }
    public static DisplayService getInstance() {
        return Holder.INSTANCE;
    }

}
