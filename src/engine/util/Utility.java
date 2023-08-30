package engine.util;

import engine.board.*;

public class Utility {
    //private constructor since it is a utility class
    private Utility() {

    }

    public static boolean checkIfInBounds(Position curr, int newX, int newY) {
        if(curr.getX() + newX >= 0 && curr.getX() + newX <= 7 &&
                curr.getY() + newY >= 0 && curr.getY() + newY <= 7) {
            return true;
        }
        return false;
    }

    public static boolean checkIfInBounds(int newX, int newY) {
        if(newX >= 0 && newX < Board.BOARD_MAX_COLS && newY >= 0 && newY < Board.BOARD_MAX_ROWS) {
            return true;
        }
        return false;
    }

}
