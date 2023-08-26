package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public class Board {
    private Cell[][] board;

    public Board() {
        this.board = new Cell[8][8];
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new EmptyCell(new Position(i, j));
            }
        }
    }

    public Cell getCellAt(Position position) {
        return this.board[position.getX()][position.getY()];
    }

    public Cell getCellAt(int x, int y) {
        return this.board[x][y];
    }

    public Piece getPieceAt(Position position) {
        return this.board[position.getX()][position.getY()].getPiece();
    }

    public Piece getPieceAt(int x, int y) {
        return this.board[x][y].getPiece();
    }
}
