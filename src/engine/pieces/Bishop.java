package engine.pieces;

import engine.Team;
import engine.board.*;
import engine.util.Position;
import engine.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Piece copy() {
        return new Bishop(new Position(this.position), team);
    }

    @Override
    public List<Move> calculateMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();
        int currX = this.position.getX();
        int currY = this.position.getY();
        //top-left
        checkLine(board, possibleMoves, currX-1,currY-1,-1, -1);
        //top-right
        checkLine(board, possibleMoves, currX+1,currY-1,1, -1);
        //bottom-left
        checkLine(board, possibleMoves, currX-1,currY+1,-1, 1);
        //bottom-right
        checkLine(board, possibleMoves, currX+1,currY+1,1, 1);

        return possibleMoves;
    }

    @Override
    public String getPieceFilename() {
        return getTeam() == Team.WHITE ? "wb.png" : "bb.png";
    }

    private void checkLine(Board board, List<Move> possibleMoves, int currX, int currY, int dx, int dy) {
        if(!Utility.checkIfInBounds(currX,currY)) {
            return;
        }
        if(Board.calculateMoveType(board, this, currX, currY, possibleMoves)) {
            checkLine(board, possibleMoves, currX + dx, currY + dy, dx, dy);
        }

    }


    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Bishop)) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "B";
    }
}
