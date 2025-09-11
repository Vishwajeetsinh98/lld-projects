import board.Board;
import piece.*;
import puzzle.Puzzle;

public class Main {
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle();
        Board board = new Board(4, 4);
        puzzle.setBoard(board);
        puzzle.reset();

        PuzzlePiece piece1 = PieceFactory.createPiece(PieceType.CORNER);
        PuzzlePiece piece2 = PieceFactory.createPiece(PieceType.EDGE);
        piece1.addEdge(Orientation.NORTH, EdgeType.FLAT, 0);
        piece1.addEdge(Orientation.WEST, EdgeType.FLAT, 0);
        piece1.addEdge(Orientation.EAST, EdgeType.INDENTATION, 4);
        piece1.addEdge(Orientation.SOUTH, EdgeType.INDENTATION, 4);

        piece2.addEdge(Orientation.NORTH, EdgeType.FLAT, 0);
        piece2.addEdge(Orientation.WEST, EdgeType.EXTRUSION, 4);
        piece2.addEdge(Orientation.SOUTH, EdgeType.INDENTATION, 4);
        piece2.addEdge(Orientation.EAST, EdgeType.EXTRUSION, 4);

        puzzle.addPiece(piece1);
        puzzle.addPiece(piece2);
        piece2.setOrientation(Orientation.EAST);

        puzzle.placePiece(0, 0, piece1);
        puzzle.placePiece(0, 1, piece2);
        puzzle.fitAdjacent("a1", "a2");

    }
}