package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public abstract class Cell {

    private boolean attackedByWhite;
    private boolean attackedByBlack;

    public Cell() {
        this.attackedByWhite = false;
        this.attackedByBlack = false;
    }

    public Cell(boolean attackedByWhite, boolean attackedByBlack) {
        this.attackedByWhite = attackedByWhite;
        this.attackedByBlack = attackedByBlack;
    }

    public abstract boolean isOccupied();
    public abstract Piece getPiece();


    public boolean isAttackedByWhite() {
        return this.attackedByWhite;
    }

    public void setAttackedByWhite(boolean attackedByWhite) {
        this.attackedByWhite = attackedByWhite;
    }

    public boolean isAttackedByBlack() {
        return this.attackedByBlack;
    }

    public void setAttackedByBlack(boolean attackedByBlack) {
        this.attackedByBlack = attackedByBlack;
    }
}
