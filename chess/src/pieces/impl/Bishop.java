package pieces.impl;

import pieces.Color;
import pieces.Piece;
import pieces.PieceType;
import pieces.movestrategies.BishopMoveStrategy;

public class Bishop extends Piece {
    public Bishop(PieceType pieceType, Color color, char characterToDisplay) {
        super(pieceType, color, characterToDisplay);
        moveStrategy = new BishopMoveStrategy();
    }
}
