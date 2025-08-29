package pieces.impl;

import pieces.Color;
import pieces.Piece;
import pieces.PieceType;
import pieces.movestrategies.RookMoveStrategy;

public class Rook extends Piece {
    public Rook(PieceType pieceType, Color color, char characterToDisplay) {
        super(pieceType, color, characterToDisplay);
        moveStrategy = new RookMoveStrategy();
    }
}
