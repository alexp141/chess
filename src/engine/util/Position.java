package engine.util;

public class Position {
    private int xCoord;
    private int yCoord;

    public Position(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }


    public int getYcoord() {
        return yCoord;
    }

    public int getXCoord() {
        return xCoord;
    }
}
