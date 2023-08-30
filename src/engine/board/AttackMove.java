package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public class AttackMove extends Move {
    private Piece attacked;

    public AttackMove(Board board, Piece movingPiece, Position start, Position destination, Piece attacked) {
        super(board, movingPiece, start, destination);
        this.attacked = attacked;
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
