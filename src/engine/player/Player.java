package engine.player;

import engine.Team;
import engine.board.*;
import engine.pieces.King;
import engine.pieces.Piece;
import engine.util.Position;

import java.util.List;

public abstract class Player {
    protected Board board;
    protected List<Move> possibleMoves;
    protected List<Piece> activePieces;
    protected King playerKing;
    protected Team team;
    private boolean hasCastled;
    private boolean isChecked;

    public Player(Board board, Team team, List<Move> whitePossibleMoves) {
        this.board = board;
        this.activePieces = board.getActivePieces(team);
        this.team = team;
        this.hasCastled = false;
        this.playerKing = getPlayerKingHelper();
    }

    public abstract List<Piece> getActivePieces();


    public Team getPlayerTeam() {
        return this.team;
    }

    private King getPlayerKingHelper() {
        for (Piece piece : this.activePieces) {
            if (piece instanceof King) {
                return (King) piece;
            }
        }
        throw new RuntimeException("Error retrieving king");
    }

    public King getPlayerKing() {
        return this.playerKing;
    }

    public List<Move> getPossibleMoves() {
        return this.possibleMoves;
    }

    public abstract boolean isChecked();

    public boolean isCheckmated() {
        return isChecked() && this.possibleMoves.isEmpty() ? true : false;
    }

    public boolean isStalemated() {
        return !isChecked() && this.possibleMoves.isEmpty() ? true : false;
    }

    public boolean hasCastled() {
        return this.hasCastled;
    }

    /**
     * accepts data of a move sent from the gui and checks if that data matches any of the legal moves the player has access to
     * @return
     */
    public Move getMove(Position start, Position destination) {
        for (Move move : this.possibleMoves) {
            if (move.getStart().equals(start) && move.getDestination().equals(destination)) {
                return move;
            }
        }
        System.out.println("returning null move");
        return null;
    }

    public MoveStatus executeMove(Move move) {
        //METHOD:
        //make move on board
        //check if currentplayer is in check
        //if they are : return not ok status and UNDO MOVE
        //if they are not : return ok status and proceed
        move.executeMove();

        if (isChecked()) {
            //undo
            System.out.println("illegal move");
            move.undoMove();
            return MoveStatus.ILLEGAL_MOVE;
        }
        else {
            this.board.switchPlayers();
            move.getMovingPiece().setPieceMoved();
            return MoveStatus.LEGAL_MOVE;
        }

    }
}
