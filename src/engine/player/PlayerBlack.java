package engine.player;

import engine.Team;
import engine.board.Board;
import engine.board.Move;
import engine.pieces.King;
import engine.pieces.Piece;
import engine.util.Position;

import java.util.List;

public class PlayerBlack extends Player {
    public PlayerBlack(Board board, Team team, List<Move> blackPossibleMoves) {
        super(board, team, blackPossibleMoves);
        this.playerKing = getPlayerKing();
        this.activePieces = getActivePieces();
        this.possibleMoves = blackPossibleMoves;
    }

    @Override
    public List<Piece> getActivePieces() {
        return this.board.getActivePieces(Team.BLACK);
    }

    @Override
    public boolean isChecked() {
        Position kingPosition = this.playerKing.getPosition();
        if (this.board.getCellAt(kingPosition.getX(), kingPosition.getY()).isAttackedByWhite())
            return true;
        else
            return false;

    }

}
