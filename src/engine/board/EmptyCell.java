package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public class EmptyCell extends Cell{

    @Override
    public boolean isOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }

    @Override
    public Cell copy() {
        return new EmptyCell();
    }
}
