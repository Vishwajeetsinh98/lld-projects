package pieces.movestrategies;

import board.Board;
import board.Direction;
import board.Square;
import pieces.Piece;

import java.util.HashSet;
import java.util.Set;

public class DirectionalMoveStrategy {

    public static Set<Square> findNextMoves(Piece piece, Square square, Direction[] allowedDirections, int maxSteps) {

        Board board = Board.getInstance();
        Set<Square> ret = new HashSet<>();

        for (Direction dir : allowedDirections) {
            for (int step = 1;step <= maxSteps;step++) {
                Square next = board.getSquareRelativeTo(square, dir, step);
                if (next == null) break; // off board
                if (next.isEmpty()) {
                    ret.add(next);
                } else {
                    if (next.getPiece().getColor() != piece.getColor()) {
                        ret.add(next); // capture
                    }
                    break; // break either way
                }
            }
        }

        return ret;
    }
}
