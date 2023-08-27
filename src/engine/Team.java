package engine;

public enum Team {
    WHITE(-1),
    BLACK(1);

    private final int direction;
    Team(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
