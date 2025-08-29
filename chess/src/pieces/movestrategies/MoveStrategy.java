package pieces.movestrategies;

import board.Square;
import pieces.Piece;

import java.util.Set;

public interface MoveStrategy {
    Set<Square> findNextMoves(Piece piece, Square square);
}
