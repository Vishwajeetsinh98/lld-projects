package player;

import controller.GameController;
import pieces.Color;
import player.commands.Move;

import java.util.Stack;

public class Player {
    private final String name;
    private final Color color;
    private int moveCount;
    private final Stack<Move> moveHistory;
    private boolean check;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        moveHistory = new Stack<>();
        reset();
    }

    public void makeMove(GameController controller, Move move) {
        move.execute(controller);
        moveHistory.push(move);
        incrementMoveCount();
    }

    public void undoLastMove(GameController controller) {
        if (moveHistory.isEmpty()) return;
        Move move = moveHistory.pop();
        move.undo(controller);
    }

    public void reset() {
        moveCount = 0;
        moveHistory.clear();
        check = false;
    }

    public String getName() {
        return name;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Color getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void incrementMoveCount() {
        moveCount++;
    }

    public void decrementMoveCount() {
        moveCount--;
    }
}
