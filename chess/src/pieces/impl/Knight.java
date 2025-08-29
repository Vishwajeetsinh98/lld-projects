package pieces.impl;

import pieces.Color;
import pieces.Piece;
import pieces.PieceType;
import pieces.movestrategies.KnightMoveStrategy;

public class Knight extends Piece {
    public Knight(PieceType pieceType, Color color, char characterToDisplay) {
        super(pieceType, color, characterToDisplay);
        moveStrategy = new KnightMoveStrategy();
    }
}
