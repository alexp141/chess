package engine.pieces;

import engine.Team;
import engine.board.*;
import engine.util.Position;
import engine.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Piece copy() {
        return new Rook(new Position(this.position), team);
    }

    @Override
    public List<Move> calculateMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();
        int currX = this.position.getX();
        int currY = this.position.getY();
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

    private void calculateMoveType(Board board, int currX, int currY, List<Move> possibleMoves) {
        Cell destinationCell = board.getCellAt(currX, currY);
        if (destinationCell.isOccupied()) {
            Piece pieceAtDestination = destinationCell.getPiece();
            if (pieceAtDestination.getTeam() != this.getTeam()) {
                possibleMoves.add(new AttackMove(board, this, new Position(currX, currY), pieceAtDestination));
            }
        }
        else {
            possibleMoves.add(new PassiveMove(board, this, new Position(currX, currY)));
        }
    }

    private void checkLine(Board board, List<Move> possibleMoves, int currX, int currY, int dx, int dy) {
        if(!Utility.checkIfInBounds(currX,currY)) {
            return;
        }

        calculateMoveType(board, currX, currY, possibleMoves);
        checkLine(board, possibleMoves, currX + dx, currY + dy, dx , dy);

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Rook)) {
            return false;
        }

        return super.equals(o);
    }
}
