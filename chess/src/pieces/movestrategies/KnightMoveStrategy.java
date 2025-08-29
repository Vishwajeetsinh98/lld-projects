package pieces.movestrategies;

import board.Board;
import board.Direction;
import board.Square;
import pieces.Piece;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KnightMoveStrategy implements MoveStrategy {
    private final Map<Integer, Direction> degreeMap;
    public KnightMoveStrategy() {
        degreeMap = Map.of(
                0, Direction.NORTH,
                45, Direction.NORTH_EAST,
                90, Direction.EAST,
                135, Direction.SOUTH_EAST,
                180, Direction.SOUTH,
                225, Direction.SOUTH_WEST,
                270, Direction.WEST,
                315, Direction.NORTH_WEST
        );
    }

    @Override
    public Set<Square> findNextMoves(Piece piece, Square square) {
        Set<Square> ret = new HashSet<>();
        Board board = Board.getInstance();

        // 2 steps in 1 direction, 1 step perpendicular
        Direction[] dirs = Direction.values();
        for (int i = 0;i < dirs.length;i++) {
            Direction dir = dirs[i];
            int startingDegree = (i * 45) % 360;
            Square nextTwoStep = board.getSquareRelativeTo(square, dir, 2);
            if (nextTwoStep == null) continue;

            Direction[] nextDirs = new Direction[] {degreeMap.get((startingDegree + 90) % 360),
                                                    degreeMap.get((startingDegree - 90 + 360) % 360)};
            for (Direction nextDir : nextDirs) {
                Square nextFinalSquare = board.getSquareRelativeTo(nextTwoStep, nextDir, 1);
                if (nextFinalSquare == null) continue;
                if (nextFinalSquare.isEmpty()) {
                    ret.add(nextFinalSquare);
                } else {
                    if (nextFinalSquare.getPiece().getColor() != piece.getColor()) {
                        ret.add(nextFinalSquare);
                    }
                }
            }
        }
        return ret;
    }
}
