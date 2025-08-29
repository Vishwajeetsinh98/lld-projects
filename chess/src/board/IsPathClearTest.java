package board;

import pieces.Color;
import pieces.PieceFactory;
import pieces.PieceType;

public class IsPathClearTest {

    public static void testDirection(Board board, Direction dir, String startId, String endId) {
        System.out.println("\n=== Testing " + dir + " ===");

        // ---- Clear path case ----
        board.resetGrid();
        Square start = board.getSquareById(startId);
        Square end   = board.getSquareById(endId);

        // Ensure no blockers on the path
        clearPiecesBetween(board, start, end, dir);

        boolean clearResult = board.isPathClear(start, end, dir);
        System.out.println("Clear path (" + startId + " -> " + endId + "): " + clearResult);

        // ---- Blocked path case ----
        board.resetGrid();
        start = board.getSquareById(startId);
        end   = board.getSquareById(endId);

        clearPiecesBetween(board, start, end, dir);
        // Drop a blocking piece in the middle
        Square mid = getMidSquare(board, start, end, dir);
        mid.setPiece(PieceFactory.createPiece(PieceType.PAWN, Color.WHITE));

        boolean blockedResult = board.isPathClear(start, end, dir);
        System.out.println("Blocked path (" + startId + " -> " + endId + "): " + blockedResult);
    }

    /** Utility: clears all intermediate squares between start and end */
    private static void clearPiecesBetween(Board board, Square start, Square end, Direction dir) {
        int[] vec = getDirectionVector(dir);
        Square curr = start;

        while (curr != end) {
            int nx = curr.getX() + vec[0];
            int ny = curr.getY() + vec[1];
            curr = board.getGridPosMatrix()[nx][ny];
            if (curr != end) {
                curr.setPiece(null); // clear intermediate square
            }
        }
    }

    /** Utility: get a middle square between start and end */
    private static Square getMidSquare(Board board, Square start, Square end, Direction dir) {
        int[] vec = getDirectionVector(dir);
        int sx = start.getX();
        int sy = start.getY();
        int ex = end.getX();
        int ey = end.getY();

        // Just step half-way along
        int steps = Math.max(Math.abs(ex - sx), Math.abs(ey - sy)) / 2;
        int mx = sx + steps * vec[0];
        int my = sy + steps * vec[1];
        return board.getGridPosMatrix()[mx][my];
    }

    private static int[] getDirectionVector(Direction direction) {
        return switch (direction) {
            case NORTH      -> new int[] {-1,  0};
            case SOUTH      -> new int[] { 1,  0};
            case EAST       -> new int[] { 0,  1};
            case WEST       -> new int[] { 0, -1};
            case NORTH_EAST -> new int[] {-1,  1};
            case NORTH_WEST -> new int[] {-1, -1};
            case SOUTH_EAST -> new int[] { 1,  1};
            case SOUTH_WEST -> new int[] { 1, -1};
        };
    }

}
