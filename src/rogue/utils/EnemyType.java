package pt.upskill.projeto1.rogue.utils;

public enum EnemyType {
    SKELETON(5,10),
    PRISONER(5,10),
    THIEF(5,10),
    EVILBAT(5, 10),
    BOSS(30, 100);

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
