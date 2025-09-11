package piece;

public class PieceFactory {
    // TODO: Have a method that creates the edges too.
    public static PuzzlePiece createPiece(PieceType pieceType) {
        return new PuzzlePiece(pieceType);
    }
}
