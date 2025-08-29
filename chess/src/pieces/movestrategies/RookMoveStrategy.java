package pieces.movestrategies;

import board.Direction;
import board.Square;
import pieces.Piece;

import java.util.HashSet;
import java.util.Set;

public class RookMoveStrategy implements MoveStrategy {

    @Override
    public Set<Square> findNextMoves(Piece piece, Square square) {
        Set<Square> ret;
        Direction[] dirs = new Direction[]{Direction.NORTH, Direction.SOUTH,
                                           Direction.EAST, Direction.WEST};

        ret = new HashSet<>(DirectionalMoveStrategy.findNextMoves(piece, square, dirs, Integer.MAX_VALUE));

        return ret;

    }
}
