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

    private void checkLine(Board board, List<Move> possibleMoves, int currX, int currY, int dx, int dy) {
        if(!Utility.checkIfInBounds(currX,currY)) {
            return;
        }
        //TODO need to make sure this stops after encountering an occupied cell, make calculateMoveType return a boolean
        calculateMoveType(board, currX, currY, possibleMoves);
        checkLine(board, possibleMoves, currX + dx, currY + dy, dx , dy);

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
}
