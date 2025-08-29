package player.commands;

import board.Square;
import controller.GameController;
import pieces.Piece;
import player.Player;

public class Move implements GameCommand {

    private final Player player;
    private final Square from;
    private final Square to;
    private Piece capturedPiece;
    private final Piece movePiece;

    public Move(Square from, Square to, Player player) {
        if (from.isEmpty())
            throw new IllegalArgumentException("Illegal starting square - empty");
        movePiece = from.getPiece();
        this.from = from;
        this.to = to;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Square getFrom() {
        return from;
    }

    public Square getTo() {
        return to;
    }

    public Piece getMovePiece() {
        return movePiece;
    }

    public Piece getCapturedPiece() {
        return capturedPiece;
    }

    public void setCapturedPiece(Piece capturedPiece) {
        this.capturedPiece = capturedPiece;
    }

    @Override
    public void execute(GameController controller) {
        System.out.println("[Move] Moving from: " + from + " to: " + to);
        controller.processMove(this);
    }

    @Override
    public void undo(GameController controller) {
        // TODO: Implement this.
    }
}
