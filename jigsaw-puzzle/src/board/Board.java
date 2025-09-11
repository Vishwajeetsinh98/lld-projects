package board;

import piece.Orientation;
import piece.PuzzlePiece;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Board {
    private final int rows;
    private final int cols;
    private final Cell[][] boardMatrix;
    private final Map<String, Cell> cellIdMap;
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cellIdMap = new ConcurrentHashMap<>();
        boardMatrix = new Cell[rows][cols];
    }

    private void checkBounds(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols)
            throw new IllegalArgumentException("Row/col combination out of grid bounds!");
    }

    public void resetBoard() {
        System.out.println("Resetting board");
        char rowId = 'a';
        for (int row = 0;row < rows;row++) {
            for (int col = 0;col < cols;col++) {
                String id = rowId + "" + (col + 1);
                boardMatrix[row][col] = new Cell(row, col, id);
                cellIdMap.put(id, boardMatrix[row][col]);
                System.out.print(id + "(" + row + ", " + col + ")\t");
            }
            rowId++;
            System.out.println();
        }
    }

    public boolean fit(String cell1Id, String cell2Id) {
        Cell cell1 = cellIdMap.getOrDefault(cell1Id, null);
        Cell cell2 = cellIdMap.getOrDefault(cell2Id, null);
        if (cell1 == null || cell2 == null)
            throw new IllegalArgumentException("Cell Ids not found for " + (cell1 == null ? "cell1" : "cell2"));

        if (cell1.getPiece() == null || cell2.getPiece() == null)
            throw new IllegalArgumentException("Cannot merge cells with empty cells");

        if (!checkAdjacent(cell1, cell2))
            throw new IllegalArgumentException("Cannot merge non-adjacent cells");

        System.out.println("[Board] " + (cell1.getPiece()==cell2.getPiece()));
        if (cell1.getPiece().fit(cell2.getPiece(), getAdjacencyDirection(cell1, cell2))) {
            System.out.println("[Board] " + cell1Id + " and " + " " + cell2Id + " pieces fitted!");
            return true;
        }
        else
            System.out.println("[Board] " + cell1Id + " and " + cell2Id + " pieces do not match!");
        return false;
    }

    private boolean checkAdjacent(Cell cell1, Cell cell2) {
        return (Math.abs(cell1.getRow() - cell2.getRow()) == 1 ||
                Math.abs(cell1.getCol() - cell2.getCol()) == 1);
    }

    private Orientation getAdjacencyDirection(Cell cell1, Cell cell2) {
        if (cell1.getCol() + 1 == cell2.getCol())
            return Orientation.EAST;
        if (cell1.getCol() - 1 == cell2.getCol())
            return Orientation.WEST;
        if (cell1.getRow() + 1 == cell2.getRow())
            return Orientation.SOUTH;
        return Orientation.NORTH;
    }

    public void placePiece(int row, int col, PuzzlePiece piece) {
        System.out.println("[Board] Placing new piece at: (" + row + ", " + col + ")");
        if (boardMatrix[row][col].getPiece() != null)
            throw new IllegalArgumentException("Place on the board is already occupied");
        checkBounds(row, col);
        boardMatrix[row][col].setPiece(piece);
    }

    public void removePiece(int row, int col) {
        System.out.println("[Board] Placing piece from: (" + row + ", " + col + ")");
        checkBounds(row, col);
        boardMatrix[row][col] = null;
    }
}
