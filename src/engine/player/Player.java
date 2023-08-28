package engine.player;

import engine.Team;
import engine.board.Board;
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

    public Player(Board board, Team team) {
        this.board = board;
        this.team = team;
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

}
