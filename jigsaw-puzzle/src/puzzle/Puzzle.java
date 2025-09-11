package puzzle;

import board.Board;
import piece.PuzzlePiece;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Puzzle {
    private Board board;
    private final Map<String, PuzzlePiece> pieces;

    public Puzzle() {
        pieces = new ConcurrentHashMap<>();
    }

    public Board getBoard() { return board; }

    public void setBoard(Board board) { this.board = board; }

    public void reset() {
        board.resetBoard();
    }

    public void addPiece(PuzzlePiece piece) {
        pieces.put(piece.getId(), piece);
    }

    public PuzzlePiece getPiece(String id) {
        return pieces.getOrDefault(id, null);
    }

    public void placePiece(int row, int col, String id) {
        if (!pieces.containsKey(id)) {
            System.out.println("[Puzzle] Piece id doesn't exist");
            return;
        }
        placePiece(row, col, pieces.get(id));
    }

    public void placePiece(int row, int col, PuzzlePiece piece) {
        board.placePiece(row, col, piece);
    }

    public void fitAdjacent(String cell1, String cell2) {
        board.fit(cell1, cell2);
    }

}
