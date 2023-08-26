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
    public List<Move> calculateMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();
        int currX = this.position.getX();
        int currY = this.position.getY();

        while(Utility.checkIfInBounds(++currX, currY)) {
            calculateMoveType(board, currX, currY, possibleMoves);
        }
        //reset coordinate
        currX = this.position.getX();
        while(Utility.checkIfInBounds(--currX, currY)) {
            calculateMoveType(board, currX, currY, possibleMoves);
        }
        //reset coordinate
        currX = this.position.getX();
        while(Utility.checkIfInBounds(currX, ++currY)) {
            calculateMoveType(board, currX, currY, possibleMoves);
        }
        //reset coordinate
        currY = this.position.getY();
        while(Utility.checkIfInBounds(currX, --currY)) {
            calculateMoveType(board, currX, currY, possibleMoves);
        }
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
}
