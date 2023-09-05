package engine.pieces;

import engine.Team;
import engine.board.*;
import engine.util.Position;
import engine.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(Position position, Team team) {
        super(position, team);
    }

    @Override
    public String getPieceFilename() {
        return getTeam() == Team.WHITE ? "wq.png" : "bq.png";
    }

    @Override
    public Piece copy() {
        return new Queen(new Position(this.position), team);
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
        //up
        checkLine(board, possibleMoves, currX,currY-1,0, -1);
        //down
        checkLine(board, possibleMoves, currX,currY+1,0, 1);
        //left
        checkLine(board, possibleMoves, currX-1,currY,-1, 0);
        //right
        checkLine(board, possibleMoves, currX+1,currY,1, 0);

        return possibleMoves;
    }

    private void checkLine(Board board, List<Move> possibleMoves, int currX, int currY, int dx, int dy) {
        if(!Utility.checkIfInBounds(currX,currY)) {
            return;
        }

        if (Board.calculateMoveType(board, this, currX, currY, possibleMoves)) {
            checkLine(board, possibleMoves, currX + dx, currY + dy, dx, dy);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Queen)) {
            return false;
        }

        return super.equals(o);
    }
}
