package pt.upskill.projeto1.rogue.utils;

public enum Points {
    MOVE(-1),
    DEFEAT_ENEMY(25),
    COLLECT_ITEM(5),
    COLLECT_KEY(10),
    PASS_ROOM(10),
    PASS_LOCKED_ROOM(15);

    private final int points;

    Points(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
