package engine.board;

import engine.Team;
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
        System.out.println(this.board.toString());
        movingPiece.getPosition().updatePosition(destination.getX(),destination.getY());
        if (attacked.getTeam() == Team.WHITE) {
            this.board.getActiveWhitePieces().remove(attacked);
        }
        else {
            this.board.getActiveBlackPieces().remove(attacked);
        }
        this.board.removePiece(start.getX(), start.getY());
        System.out.println(this.board.toString());
        this.board.updatePossibleMovesWhite();
        this.board.updatePossibleMovesBlack();
        this.board.updateCellData();

    }

    @Override
    public void undoMove() {
        this.board.placePiece(movingPiece, start.getX(), start.getY());
        System.out.println(this.board.toString());
        movingPiece.getPosition().updatePosition(start.getX(), start.getY());
        this.board.placePiece(attacked, destination.getX(), destination.getY());
        System.out.println(this.board.toString());
        attacked.getPosition().updatePosition(destination.getX(), destination.getY());
        if (attacked.getTeam() == Team.WHITE) {
            this.board.getActiveWhitePieces().remove(attacked);
        }
        else {
            this.board.getActiveBlackPieces().remove(attacked);
        }
        this.board.updatePossibleMovesWhite();
        this.board.updatePossibleMovesBlack();
        this.board.updateCellData();

    }
}
