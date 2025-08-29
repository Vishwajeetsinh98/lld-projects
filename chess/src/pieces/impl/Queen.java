package pieces.impl;

import pieces.Color;
import pieces.Piece;
import pieces.PieceType;
import pieces.movestrategies.QueenMoveStrategy;

public class Queen extends Piece {
    public Queen(PieceType pieceType, Color color, char characterToDisplay) {
        super(pieceType, color, characterToDisplay);
        moveStrategy = new QueenMoveStrategy();
    }
}
