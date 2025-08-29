package pieces.movestrategies;

import board.Direction;
import board.Square;
import pieces.Piece;

import java.util.HashSet;
import java.util.Set;

public class QueenMoveStrategy implements MoveStrategy {

    @Override
    public Set<Square> findNextMoves(Piece piece, Square square) {
        Set<Square> ret;

        ret = new HashSet<>(DirectionalMoveStrategy.findNextMoves(piece, square, Direction.values(), Integer.MAX_VALUE));
        return ret;
    }
}
