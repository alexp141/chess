package engine.pieces;

import engine.Team;
import engine.board.Board;
import engine.board.Move;
import engine.util.Position;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected Position position;
    protected Team team;
    protected boolean isFirstMove;

    public Piece(Position position, Team team) {
        this.position = position;
        this.team = team;
        this.isFirstMove = true;
    }

    public abstract Piece copy();
    public abstract List<Move> calculateMoves(Board board);
    public abstract String getPieceFilename();

    public Team getTeam() {
        return team;
    }

    public Position getPosition() {
        return this.position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Piece)) {
            return false;
        }

        Piece p = (Piece) o;
        return p.position.equals(this.position) && p.team == this.team && p.isFirstMove == this.isFirstMove;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, team, isFirstMove);
    }
}
