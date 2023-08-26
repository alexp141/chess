package engine.pieces;

import engine.Team;
import engine.board.Board;
import engine.board.Move;
import engine.util.Position;

import java.util.List;

public abstract class Piece {
    protected Position position;
    protected Team team;

    public Piece(Position position, Team team) {
        this.position = position;
        this.team = team;
    }

    public abstract List<Move> calculateMoves(Board board);

    public Team getTeam() {
        return team;
    }
}