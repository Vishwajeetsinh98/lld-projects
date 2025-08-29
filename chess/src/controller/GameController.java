package controller;

import board.Board;
import board.Square;
import controller.movehandlers.*;
import player.Player;
import player.commands.Move;

import java.util.HashSet;
import java.util.Set;

public class GameController {
    private GameState gameState;
    private final Board board;
    private Player white;
    private Player black;
    private MoveHandler moveHandler;
    private Player currentPlayer;

    public GameController() {
        board = Board.getInstance();
        white = null;
        black = null;
        buildHandlerChain();
        gameState = GameState.ACTIVE;
    }

    public void processMove(Move move) {
        moveHandler.handle(move, this);
    }

    private void buildHandlerChain() {
        MoveHandler preMoveValidationHandler = new PreMoveValidationHandler();
        MoveHandler stalemateHandler = new StalemateHandler();
        MoveHandler moveExecutionHandler = new MoveExecutionHandler();
        MoveHandler postMoveValidationHandler = new PostMoveValidationHandler();

        preMoveValidationHandler.setNext(stalemateHandler);
        stalemateHandler.setNext(moveExecutionHandler);
        moveExecutionHandler.setNext(postMoveValidationHandler);

        moveHandler = preMoveValidationHandler;
    }

    public Set<Move> getAllLegalMoves(Player player) {
        Set<Move> ret = new HashSet<>();
        char[] files = "abcdefgh".toCharArray();
        int[] ranks = new int[]{1,2,3,4,5,6,7,8};
        for (char file : files) {
            for (int rank : ranks) {
                Square square = board.getSquareById(file + "_" + rank);
                if (!square.isEmpty() && square.getPiece().getColor() == player.getColor()) {
                    Set<Square> targets = square.getPiece().findNextMoves(square);
                    if (!targets.isEmpty()) {
                        for (Square target : targets) {
                            ret.add(new Move(square, target, player));
                        }
                    }
                }
            }
        }
        return ret;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setInCheck(Player player, boolean check) {
        setGameState(GameState.CHECK);
        player.setCheck(check);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchTurn() {
        if (currentPlayer == white)
            currentPlayer = black;
        else currentPlayer = white;
    }

    public Player getWhite() {return white;}
    public Player getBlack() {return black;}

    public void addPlayer(Player player) {
        if (player == null) return;
        switch (player.getColor()) {
            case WHITE:
                white = player;
            case BLACK:
                black = player;
        }
        currentPlayer = white;
    }

    public void removePlayer(Player player) {
        if (player == null) return;
        switch (player.getColor()) {
            case WHITE:
                white = null;
            case BLACK:
                black = null;
        }
    }
}
