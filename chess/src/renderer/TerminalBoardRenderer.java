package renderer;

import board.Board;
import board.Square;
import pieces.Piece;

public class TerminalBoardRenderer implements BoardRenderer {

    private static final String WHITE_BG = "\u001B[47m";
    private static final String BLACK_BG = "\u001B[100m";
    private static final String RESET = "\u001B[0m";

    @Override
    public void render(Board board) {
        Square[][] grid = board.getGridPosMatrix();

        System.out.println("  a  b  c  d  e  f  g  h");
//        for (int rank = 7; rank >= 0; rank--) {  // rank 7 = row 8
//            StringBuilder row = new StringBuilder();
//            for (int file = 0; file < 8; file++) {
//                Square square = grid[file][rank]; // file first, rank second
//                String bg = ((rank + file) % 2 == 0) ? WHITE_BG : BLACK_BG;
//                Piece pieceAtGrid = square.getPiece();
//                String piece = pieceAtGrid == null ? " " : "" + pieceAtGrid.getCharacterToDisplay();
//                row.append(bg).append(" ").append(piece).append(" ").append(RESET);
//            }
//            System.out.println((rank + 1) + " " + row + " " + (rank + 1));
//        }

        for (int row = 0; row < 8; row++) {
            StringBuilder rowPrint = new StringBuilder();
            int rank = 8 - row;
            for (int col = 0;col < 8;col++) {
                Square square = grid[row][col];
                String bg = ((row + col) % 2 == 0) ? WHITE_BG : BLACK_BG;
                Piece pieceAtGrid = square.getPiece();
                String piece = pieceAtGrid == null ? " " : "" + pieceAtGrid.getCharacterToDisplay();
                rowPrint.append(bg).append(" ").append(piece).append(" ").append(RESET);
            }
            System.out.println(rank + " " + rowPrint + " " + rank);
        }

        System.out.println("  a  b  c  d  e  f  g  h");
    }
}
