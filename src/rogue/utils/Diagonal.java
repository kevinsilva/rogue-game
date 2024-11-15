package pt.upskill.projeto1.rogue.utils;

public enum Diagonal {
    UP_LEFT, UP_RIGHT, DOWN_RIGHT, DOWN_LEFT;

    public Vector2D asVector() {
        if (this == Diagonal.UP_LEFT) return new Vector2D(-1, 1);
        if (this == Diagonal.UP_RIGHT) return new Vector2D(1, 1);
        if (this == Diagonal.DOWN_LEFT) return new Vector2D(-1, -1);
        if (this == Diagonal.DOWN_RIGHT) return new Vector2D(1, -1);

        return null;
    }
}
