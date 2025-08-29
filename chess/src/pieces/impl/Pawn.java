package pieces.impl;

import pieces.Color;
import pieces.Piece;
import pieces.PieceType;
import pieces.movestrategies.PawnMoveStrategy;

public class Pawn extends Piece {
    public Pawn(PieceType pieceType, Color color, char characterToDisplay) {
        super(pieceType, color, characterToDisplay);
        moveStrategy = new PawnMoveStrategy();
    }
}
