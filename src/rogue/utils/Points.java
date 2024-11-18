package pt.upskill.projeto1.rogue.utils;

public enum Points {
    MOVE(-1),
    DEFEAT_ENEMY(50),
    COLLECT_ITEM(10),
    COLLECT_KEY(15),
    PASS_ROOM(20),
    PASS_LOCKED_ROOM(30),
    RESCUE_PRINCESS(100);

    private final int points;

    Points(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
