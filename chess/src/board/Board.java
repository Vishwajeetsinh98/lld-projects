package board;

import pieces.Color;
import pieces.PieceFactory;
import pieces.PieceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Board {

    private final Map<String, Square> gridIdMap;
    private final Square[][] gridPosMatrix;
    private Board() {
        gridIdMap = new ConcurrentHashMap<>();
        gridPosMatrix = new Square[8][8];
        resetGrid();
    }

    public void resetGrid() {
        gridIdMap.clear();
        for(int i = 0;i < 8;i++)
            Arrays.fill(gridPosMatrix[i], null);
        for (int row = 0; row < 8; row++) {
            int rank = 8 - row;
            for (int col = 0; col < 8; col++) {
                char file = (char) ('a' + col);
                Square square = new Square(file + "_" + rank, row, col, null);
                square.setPiece(PieceFactory.createInitialPiece(file, rank));
                gridPosMatrix[row][col] = square;
                gridIdMap.put(square.getId(), square);
            }
        }
    }

    public Square getSquareById(String id) {
        return gridIdMap.getOrDefault(id, null);
    }

    public List<Square> getAllSquares() {
        return gridIdMap.values().stream().toList();
    }

    private int[] getDirectionVector(Direction direction) {
        return switch(direction) {
            case NORTH -> new int[] {-1, 0};
            case SOUTH -> new int[] {1, 0};
            case EAST -> new int[] {0, 1};
            case WEST -> new int[] {0, -1};
            case NORTH_EAST -> new int[] {-1, 1};
            case NORTH_WEST -> new int[] {-1, -1};
            case SOUTH_EAST -> new int[] {1, 1};
            case SOUTH_WEST -> new int[] {1, -1};
        };
    }

    public Square getKingSquare(Color color) {
        for (Square square : gridIdMap.values()) {
            if (!square.isEmpty() && square.getPiece().getPieceType() == PieceType.KING && square.getPiece().getColor() == color)
                return square;
        }
        return null;
    }

    public Square getSquareRelativeTo(Square square, Direction direction, int steps) {
        if (steps <= 0) return null;
        int [] dir = getDirectionVector(direction);
        int nextX = -1;
        int nextY = -1;
        for (int i = 0;i < steps;i++) {
            nextX = square.getX() + dir[0];
            nextY = square.getY() + dir[1];
            if (nextX < 0 || nextX > 7 ||
                    nextY < 0 || nextY > 7) return null;
            square = gridPosMatrix[nextX][nextY];
        }
        return square;
    }

    public boolean isPathClear(Square start, Square end, Direction direction) {
        int[] dir = getDirectionVector(direction);

        System.out.println("[Board] isPathClear: " + start  + " -> " + end + " " + direction);

        Square curr = start;
        while (curr != end) {
            int x = curr.getX();
            int y = curr.getY();

            int nextX = x + dir[0];
            int nextY = y + dir[1];

            if (nextX < 0 || nextX > 7 ||
                nextY < 0 || nextY > 7) {
                System.out.println("[Board] isPathClear: Off grid: (" + nextX + ", " + nextY + ")");
                return false;
            }

            curr = gridPosMatrix[nextX][nextY];
            System.out.println("[Board] isPathClear: Waypoint: " + curr);
            if (curr != end && curr.getPiece() != null) {
                System.out.println("[Board] isPathClear: Waypoint " + curr + " not empty");
                return false;
            }
        }
        return true;
    }

    public Square[][] getGridPosMatrix() {
        return gridPosMatrix;
    }

    // Singleton:
    private static final class Holder {
        private static final Board INSTANCE = new Board();
    }

    public static Board getInstance() {
        return Holder.INSTANCE;
    }
}
