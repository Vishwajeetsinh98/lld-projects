package pieces;

import board.Square;
import pieces.movestrategies.MoveStrategy;

import java.util.Set;

public abstract class Piece {
    protected final PieceType pieceType;
    protected final char characterToDisplay;
    protected final Color color;
    protected boolean isAlive;
    protected int moveCount;
    protected MoveStrategy moveStrategy;

    public Piece(PieceType pieceType, Color color, char characterToDisplay) {
        this.pieceType = pieceType;
        this.color = color;
        this.characterToDisplay = characterToDisplay;
        isAlive = true;
        moveCount = 0;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }

    public char getCharacterToDisplay() {
        return characterToDisplay;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void incrementMoveCount() {
        moveCount++;
    }

    public Set<Square> findNextMoves(Square square) {
        return moveStrategy.findNextMoves(this, square);
    }

    @Override
    public String toString() {
        return color + " " + pieceType;
    }
}
