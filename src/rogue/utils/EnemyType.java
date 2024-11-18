package pt.upskill.projeto1.rogue.utils;

public enum EnemyType {
    SKELETON(10,10),
    PRISONER(15,20),
    THIEF(8,25),
    EVILBAT(6, 10),
    BOSS(30, 80);

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
