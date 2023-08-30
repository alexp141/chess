package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public class PassiveMove extends Move{

    public PassiveMove(Board board, Piece movingPiece, Position start, Position destination) {
        super(board, movingPiece, start, destination);
    }

    @Override
    public Board executeMove() {
        return null;
    }

    @Override
    public Board undoMove() {
        return null;
    }

}
