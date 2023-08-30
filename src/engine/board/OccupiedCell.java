package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public class OccupiedCell extends Cell {
    private Piece piece;

    public OccupiedCell(Piece piece) {
        super();
        this.piece = piece;
    }

    public OccupiedCell(boolean attackedByWhite, boolean attackedByBlack, Piece piece) {
        super(attackedByWhite, attackedByBlack);
        this.piece = piece;
    }

    @Override
    public boolean isOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return piece;
    }

    @Override
    public Cell copy() {
        return new OccupiedCell(piece.copy());
    }


}
