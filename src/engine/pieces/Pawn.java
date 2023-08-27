package engine.pieces;

import engine.Team;
import engine.board.*;
import engine.util.Position;
import engine.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private final int[][] offsets = {{-1,-1}, {1,-1}, {0,-1}, {0,-2}};
    public Pawn(Position position, Team team) {
        super(position, team);
    }

    @Override
    public List<Move> calculateMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();

        for (int[] offset : offsets) {
            int x = offset[0];
            int y = offset[1];
            //get new direction based on whether the team is black or white
            int dx = x * (this.team.getDirection() * -1); //x direction that the piece will move in
            int dy = y * (this.team.getDirection() * -1); //y direction that the piece will move in
            Position destination = new Position(this.position.getX() + dx, this.position.getY() + dy);
            //check bounds
            if(!Utility.checkIfInBounds(destination.getX(),destination.getY())) {
                continue;
            }
            //frontal single move
            if(x == 0 && y == -1) {
                Utility.calculateMoveType(board, this, destination.getX(), destination.getY(), possibleMoves);
            }
            //diagonal attacks
            else if (x == -1 && y == -1 || x == 1 && y == -1) {
                //attack only if enemy piece is there
                Cell destinationCell = board.getCellAt(destination.getX(), destination.getY());
                if (destinationCell.isOccupied()) {
                    Piece pieceAtDestination = destinationCell.getPiece();
                    if (pieceAtDestination.getTeam() != this.getTeam()) {
                        possibleMoves.add(new AttackMove(board, this, new Position(x, y), pieceAtDestination));
                    }
                }
            }
            //frontal double move
            else if ((x == 0 && y == -2) && this.isFirstMove && ((this.getTeam() == Team.BLACK && this.position.getX() == Board.BOARD_SECOND_ROW) ||
                    this.getTeam() == Team.WHITE && this.position.getX() == Board.BOARD_SEVENTH_ROW)) {
                
            }

        }

        return possibleMoves;
    }
}
