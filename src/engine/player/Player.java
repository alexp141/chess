package engine.player;

import engine.Team;
import engine.board.Board;
import engine.board.Cell;
import engine.board.Move;
import engine.pieces.King;
import engine.pieces.Piece;

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
        this.team = team;
        this.hasCastled = false;
    }

    public abstract List<Piece> getActivePieces();

    public abstract List<Move> getOpponentMoves();
    public Team getPlayerTeam() {
        return this.team;
    }
    public King getPlayerKing() {
        for (Piece piece : this.activePieces) {
            if (piece instanceof King) {
                return (King) piece;
            }
        }
        throw new RuntimeException("Error retrieving king");
    }

    public List<Move> getPossibleMoves() {
        return this.board.getPossibleMoves(this.activePieces);
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public boolean isCheckmated() {
        return false;
    }

    public boolean isStalemated() {
        return this.isChecked && this.getPossibleMoves().isEmpty();
    }

    public boolean hasCastled() {
        return this.hasCastled;
    }

    public Cell[][] executeMove(Move move) {
        //check if move is legal

        //make move on futureboard (copy of current board)
        //check if that move leaves player checked
        //if it does return same board (same board, Move status) obj
        //if the move is okay, return futureboard (new board, Move status) obj
        //refresh board variables?
        //update cellData

        return this.board.getBoard();
    }
}
