package pt.upskill.projeto1.rogue.utils;

public enum EnemyType {
    SKELETON(5,10),
    BADGUY(5,10),
    THIEF(5,10);

    private final int power;
    private final int health;

    EnemyType(int power, int health) {
        this.power = power;
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public int getHealth() {
        return health;
    }
}
