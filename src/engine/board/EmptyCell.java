package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public class EmptyCell extends Cell{
    public EmptyCell(Position position) {
        super(position);
    }

    @Override
    public boolean isOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }
}
