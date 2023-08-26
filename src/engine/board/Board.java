package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public class Board {
    private Cell[][] board;
    public static final int BOARD_MAX_ROWS = 8;
    public static final int BOARD_MAX_COLS = 8;
    public Board() {
        this.board = new Cell[8][8];
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < BOARD_MAX_ROWS; i++) {
            for (int j = 0; j < BOARD_MAX_COLS; j++) {
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

    public void placePiece(Piece piece, int x, int y) {
        this.board[x][y] = new OccupiedCell(new Position(x,y), piece);
    }
}
