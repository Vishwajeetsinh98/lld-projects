package controller.movehandlers;

import board.Board;
import board.Square;
import controller.GameController;
import pieces.Color;
import pieces.Piece;
import player.Player;
import player.commands.Move;

import java.util.Set;

public class PostMoveValidationHandler extends MoveHandler {
    @Override
    public void handle(Move move, GameController controller) {
        System.out.println("[PostMoveValidationHandler] Game completion detection");

        Player player = move.getPlayer();
        Player opponent = player.getColor() == Color.BLACK ? controller.getWhite() : controller.getBlack();
        Square opponentKingSquare = Board.getInstance().getKingSquare(opponent.getColor());


        if (isSquareAttacked(opponentKingSquare, player, controller)) {
            System.out.println("[PostMoveExecutionHandler] " + opponent.getColor() + " is in CHECK!");
            controller.setInCheck(opponent, true);
        } else {
            controller.setInCheck(opponent, false);
        }

        // TODO: Add checkmate, stalemate etc checks

    }

    private boolean isSquareAttacked(Square target, Player attacker, GameController controller) {
        Board board = Board.getInstance();
        for (Square square : board.getAllSquares()) {
            if (!square.isEmpty() && square.getPiece().getColor() == attacker.getColor()) {
                Piece piece = square.getPiece();
                Set<Square> possibleMoves = piece.findNextMoves(square);
                for (Square s : possibleMoves) {
                    if (s.equals(target)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
