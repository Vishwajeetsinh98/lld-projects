package pieces.movestrategies;

import board.Board;
import board.Direction;
import board.Square;
import pieces.Piece;

import java.util.HashSet;
import java.util.Set;

public class BishopMoveStrategy implements MoveStrategy {

    @Override
    public Set<Square> findNextMoves(Piece piece, Square square) {
        Set<Square> ret;
        Board board = Board.getInstance();
        Direction[] dirs = new Direction[] {Direction.NORTH_EAST, Direction.NORTH_WEST,
                                            Direction.SOUTH_EAST, Direction.SOUTH_WEST};

        ret = new HashSet<>(DirectionalMoveStrategy.findNextMoves(piece, square, dirs, Integer.MAX_VALUE));

        return ret;
    }
}
