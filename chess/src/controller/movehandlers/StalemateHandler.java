package controller.movehandlers;

import controller.GameController;
import controller.GameState;
import player.commands.Move;

public class StalemateHandler extends MoveHandler {

    @Override
    public void handle(Move move, GameController controller) {
        if (controller.getGameState() != GameState.CHECK && controller.getAllLegalMoves(move.getPlayer()).isEmpty())
            throw new IllegalArgumentException("Stalemate!");
        if (controller.getWhite().getMoveCount() == 50 && controller.getBlack().getMoveCount() == 50)
            throw new IllegalArgumentException("Draw!");
        System.out.println("[StalemateHandler] No stalemate or draw, proceeding...");
        if (next != null)
            next.handle(move, controller);
    }
}
