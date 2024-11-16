package pt.upskill.projeto1.rogue.utils;

public enum Damage {
    UNARMED(5),
    SWORD(10),
    HAMMER(15),
    FIREBALL(50);

    private final int damage;

    Damage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }
}
