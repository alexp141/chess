package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public class PassiveMove extends Move {

    public PassiveMove(Board board, Piece movingPiece, Position start, Position destination) {
        super(board, movingPiece, start, destination);
    }

    @Override
    public void executeMove() {
        this.board.placePiece(movingPiece, destination.getX(), destination.getY());
        System.out.println(this.board.toString());
        this.board.removePiece(start.getX(), start.getY());
        System.out.println(this.board.toString());
        movingPiece.getPosition().updatePosition(destination.getX(),destination.getY());
        this.board.updatePossibleMovesWhite();
        this.board.updatePossibleMovesBlack();
        this.board.updateCellData();
    }

    @Override
    public void undoMove() {
        this.board.placePiece(movingPiece, start.getX(), start.getY());
        System.out.println(this.board.toString());
        movingPiece.getPosition().updatePosition(start.getX(), start.getY());
        this.board.removePiece(destination.getX(), destination.getY());
        System.out.println(this.board.toString());
        this.board.updatePossibleMovesWhite();
        this.board.updatePossibleMovesBlack();
        this.board.updateCellData();
    }

}
