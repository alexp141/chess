package engine.player;

import engine.Team;
import engine.board.Board;
import engine.board.Move;
import engine.pieces.King;
import engine.pieces.Piece;
import engine.util.Position;

import java.util.ArrayList;
import java.util.List;

public class PlayerBlack extends Player {
    public PlayerBlack(Board board, Team team, List<Move> blackPossibleMoves) {
        super(board, team, blackPossibleMoves);
        this.possibleMoves = blackPossibleMoves;
        this.activePieces = board.getActiveBlackPieces();
        this.playerKing = getPlayerKing();
    }

    @Override
    public List<Piece> getActivePieces() {
        return this.activePieces;
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
