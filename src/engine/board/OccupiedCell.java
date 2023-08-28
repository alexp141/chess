package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public class OccupiedCell extends Cell{
    private Piece piece;

    public OccupiedCell(Piece piece) {
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
}
