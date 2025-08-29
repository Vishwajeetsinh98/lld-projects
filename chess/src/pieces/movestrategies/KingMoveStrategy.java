package pieces.movestrategies;

import board.Board;
import board.Direction;
import board.Square;
import pieces.Piece;

import java.util.HashSet;
import java.util.Set;

public class KingMoveStrategy implements MoveStrategy {

    @Override
    public Set<Square> findNextMoves(Piece piece, Square square) {
        Set<Square> ret;
        Board board = Board.getInstance();

        // Generate pseudo-legal moves first, check detection later.
        ret = new HashSet<>(DirectionalMoveStrategy.findNextMoves(piece, square, Direction.values(), 1));

        // TODO: Include castling

        return ret;
    }
}
