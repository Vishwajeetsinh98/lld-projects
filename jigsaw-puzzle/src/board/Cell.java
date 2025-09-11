package board;

import piece.PuzzlePiece;

public class Cell {
    private final int row;
    private final int col;
    private final String id;
    private PuzzlePiece piece;

    public Cell(int row, int col, String id) {
        this.row = row;
        this.col = col;
        this.id = id;
    }

    public String getId() { return id; }

    public int getRow() { return row; }

    public int getCol() { return col; }

    public PuzzlePiece getPiece() { return piece; }

    public void setPiece(PuzzlePiece piece) { this.piece = piece; }
}
