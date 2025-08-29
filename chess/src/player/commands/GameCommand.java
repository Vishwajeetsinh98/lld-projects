package player.commands;

import controller.GameController;

public interface GameCommand {
    void execute(GameController controller);
    void undo(GameController controller);
}
