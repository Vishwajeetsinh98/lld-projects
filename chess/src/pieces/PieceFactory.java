package pieces;

import pieces.impl.*;

public class PieceFactory {
    public static Piece createPiece(PieceType type, Color color) {
        /*
        * ♔♕♖♗♘♙ for white pieces
        * ♚♛♜♝♞♟ for black pieces
        * */
        return switch (type) {
            case KING -> new King(type, color, color == Color.WHITE ? '♔' : '♚');
            case QUEEN -> new Queen(type, color, color == Color.WHITE ? '♕' : '♛');
            case PAWN -> new Pawn(type, color, color == Color.WHITE ? '♙' : '♟');
            case ROOK -> new Rook(type, color, color == Color.WHITE ? '♖' : '♜');
            case BISHOP -> new Bishop(type, color, color == Color.WHITE ? '♗' : '♝');
            case KNIGHT -> new Knight(type, color, color == Color.WHITE ? '♘' : '♞');
            default -> throw new IllegalArgumentException("Invalid type");
        };
    }

    public static Piece createInitialPiece(char file, int rank) {
        if (rank == 2 || rank == 7)
            return createPiece(PieceType.PAWN, rank == 2 ? Color.WHITE : Color.BLACK);
        if (rank == 1 || rank == 8) {
            return createBackRankPiece(file, rank);
        }
        return null;
    }

    private static Piece createBackRankPiece(char file, int rank) {
        Color color = rank == 1 ? Color.WHITE : Color.BLACK;
        return switch (file) {
            case 'a', 'h' -> createPiece(PieceType.ROOK, color);
            case 'b', 'g' -> createPiece(PieceType.KNIGHT, color);
            case 'c', 'f' -> createPiece(PieceType.BISHOP, color);
            case 'd' -> createPiece(PieceType.QUEEN, color);
            case 'e' -> createPiece(PieceType.KING, color);
            default -> throw new IllegalStateException("Unexpected value: " + file);
        };
    }
}
