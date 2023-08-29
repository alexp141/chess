package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public class AttackMove extends Move {
    private Piece attacked;
    public AttackMove(Board board, Piece movingPiece, Position destination, Piece attacked) {
        super(board, movingPiece, destination);
        this.attacked = attacked;
    }

    @Override
    public Board executeMove(Board board) {
        return null;
    }
}
