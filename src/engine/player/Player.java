package engine.player;

import engine.Team;
import engine.board.Board;
import engine.board.Cell;
import engine.board.Move;
import engine.board.MoveStatus;
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

    public Player(Board board, Team team) {
        this.board = board;
        this.activePieces = board.getActivePieces(team);
        this.team = team;
        this.hasCastled = false;
        this.playerKing = getPlayerKingHelper();
    }

    public abstract List<Piece> getActivePieces();

    public abstract List<Move> getOpponentMoves();

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
        return this.board.getPossibleMoves(this.activePieces);
    }

    public boolean isChecked() {
        Position kingPosition = this.playerKing.getPosition();
        if (this.team == Team.WHITE) {
            if (this.board.getCellAt(kingPosition.getX(), kingPosition.getY()).isAttackedByBlack())
                return true;
            else
                return false;
        }
        else {
            if (this.board.getCellAt(kingPosition.getX(), kingPosition.getY()).isAttackedByWhite())
                return true;
            else
                return false;
        }

    }

    public boolean isCheckmated() {
        return isChecked() && this.possibleMoves.isEmpty() ? true : false;
    }

    public boolean isStalemated() {
        return !isChecked() && this.possibleMoves.isEmpty() ? true : false;
    }

    public boolean hasCastled() {
        return this.hasCastled;
    }

    public MoveStatus executeMove(Move move) {
        //check if move is legal
        if (!this.possibleMoves.contains(move)) {
            return MoveStatus.ILLEGAL_MOVE;
        }
        //METHOD:
        //make move on board
        //check if currentplayer is in check
        //if they are : return not ok status and UNDO MOVE
        //if they are not : return ok status and proceed
        move.executeMove();

        if (isChecked()) {
            //undo
            return MoveStatus.ILLEGAL_MOVE;
        }
        else {
            this.board.switchPlayers();
            return MoveStatus.LEGAL_MOVE;
        }

    }
}
