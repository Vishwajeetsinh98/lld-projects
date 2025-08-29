package controller.movehandlers;

import board.Square;
import controller.GameController;
import pieces.Piece;
import player.commands.Move;

public class MoveExecutionHandler extends MoveHandler {
    @Override
    public void handle(Move move, GameController controller) {
        Square from = move.getFrom();
        Square to = move.getTo();
        Piece movePiece = move.getMovePiece();
        System.out.println("[MoveExecutionHandler] Handling move for " + movePiece + " from: " + from + " to: " + to);

        // Capture the piece
        if (!to.isEmpty()) {
            Piece captured = to.getPiece();
            System.out.println("[MoveExecutionHandler] Target square: " + to + " has a piece " + captured + ", capturing.");
            move.setCapturedPiece(captured);
            to.setPiece(null);
            captured.setAlive(false);
        }
        // Move the piece to the square and increment its move count.
        to.setPiece(movePiece);
        movePiece.incrementMoveCount();
        from.setPiece(null);
    }
}
