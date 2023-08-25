package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public class OccupiedCell extends Cell{
    private Piece piece;

    public OccupiedCell(Position position, Piece piece) {
        super(position);
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
