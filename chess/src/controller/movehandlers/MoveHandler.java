package controller.movehandlers;

import controller.GameController;
import player.commands.Move;


public abstract class MoveHandler {

    protected MoveHandler next;

    public abstract void handle(Move move, GameController controller);
    public void setNext(MoveHandler next) {
        this.next = next;
    }
}
