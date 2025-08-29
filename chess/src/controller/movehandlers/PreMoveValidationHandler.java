package controller.movehandlers;

import board.Square;
import controller.GameController;
import pieces.Piece;
import player.commands.Move;

import java.util.Set;

public class PreMoveValidationHandler extends MoveHandler {
    @Override
    public void handle(Move move, GameController controller) {
        // Piece should be owned by Player
        // Piece should be alive
        // Move should be in valid moves list for Piece

        System.out.println("[PreMoveValidationHandler] Checking for move validity");
        Piece movePiece = move.getFrom().getPiece();
        if (movePiece.getColor() != move.getPlayer().getColor())
            throw new IllegalArgumentException("Player " + move.getPlayer().getColor() + " cannot move " + movePiece.getColor() + " piece");
        // Extra defensive since dead pieces are already removed from the Square's
        if (!movePiece.isAlive())
            throw new IllegalArgumentException("Piece " + movePiece + " dead");

        Set<Square> validMoves = movePiece.findNextMoves(move.getFrom());
        if (!validMoves.contains(move.getTo()))
            throw new IllegalArgumentException("Target square: " + move.getTo() + " not a valid move for piece");

        System.out.println("[PreMoveValidationHandler] Checks passed, moving on..");
        if (next != null)
            next.handle(move, controller);
    }
}
