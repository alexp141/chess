package engine.player;

import engine.Team;
import engine.board.Board;
import engine.board.Move;
import engine.board.MoveStatus;
import engine.pieces.King;
import engine.pieces.Piece;
import engine.util.Position;

import java.util.ArrayList;
import java.util.List;

public class PlayerWhite extends Player {
    public PlayerWhite(Board board, Team team, List<Move> whitePossibleMoves) {
        super(board, team, whitePossibleMoves);
        this.possibleMoves = whitePossibleMoves;
        this.activePieces = board.getActiveWhitePieces();
        this.playerKing = getPlayerKing();
    }

    @Override
    public List<Piece> getActivePieces() {
        return this.activePieces;
    }

    @Override
    public boolean isChecked() {
        Position kingPosition = this.playerKing.getPosition();
        if (this.board.getCellAt(kingPosition).isAttackedByBlack())
            return true;
        else
            return false;
    }


}
