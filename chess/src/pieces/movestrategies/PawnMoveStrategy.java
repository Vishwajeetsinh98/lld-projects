package pieces.movestrategies;

import board.Board;
import board.Direction;
import board.Square;
import pieces.Color;
import pieces.Piece;

import java.util.HashSet;
import java.util.Set;

public class PawnMoveStrategy implements MoveStrategy {
    @Override
    public Set<Square> findNextMoves(Piece piece, Square square) {
        Set<Square> ret = new HashSet<>();
        Board board = Board.getInstance();
        Direction direction = piece.getColor() == Color.WHITE ? Direction.NORTH : Direction.SOUTH;

        // Initial moves:
        Square nextSquare = board.getSquareRelativeTo(square, direction, 1);
        if (nextSquare != null && nextSquare.isEmpty()) {
            ret.add(nextSquare);
            // If moving for first time, can move 2 spaces:
            if (piece.getMoveCount() == 0) {
                Square twoStep = board.getSquareRelativeTo(square, direction, 2);
                if (twoStep != null && twoStep.isEmpty())
                    ret.add(twoStep);
            }
        }

        // If there are enemy pieces at diagonals, can move there.
        Direction[] diagonals = piece.getColor() == Color.WHITE ?
                                    new Direction[]{Direction.NORTH_EAST, Direction.NORTH_WEST} :
                                        new Direction[]{Direction.SOUTH_EAST, Direction.SOUTH_WEST};
        for (Direction diagonalDirection : diagonals) {
            Square diagonalSquare = board.getSquareRelativeTo(square, diagonalDirection, 1);
            if (diagonalSquare != null && !diagonalSquare.isEmpty() && diagonalSquare.getPiece().getColor() != piece.getColor())
                ret.add(diagonalSquare);
        }

        // TODO: Add support for En-passant

        // Remove filled spaces and return
        ret.removeIf(next -> !next.isEmpty() && next.getPiece().getColor() == piece.getColor());
        return ret;
    }
}
