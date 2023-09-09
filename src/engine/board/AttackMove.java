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
    public void executeMove() {
        this.board.placePiece(movingPiece, destination.getX(), destination.getY());
        this.board.removePiece(start.getX(), start.getY());
        movingPiece.getPosition().updatePosition(destination.getX(),destination.getY());
        attacked.getPosition().updatePosition(-1,-1);
        this.board.updatePossibleMovesWhite();
        this.board.updatePossibleMovesBlack();
        this.board.updateCellData();

    }

    @Override
    public void undoMove() {
        this.board.placePiece(movingPiece, start.getX(), start.getY());
        movingPiece.getPosition().updatePosition(start.getX(), start.getY());
        this.board.placePiece(attacked, destination.getX(), destination.getY());
        attacked.getPosition().updatePosition(destination.getX(), destination.getY());
        this.board.updatePossibleMovesWhite();
        this.board.updatePossibleMovesBlack();
        this.board.updateCellData();

    }
}
