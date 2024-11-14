package pt.upskill.projeto1.objects;

import java.util.List;

public class HeroStatus {
    private int health;
    private int fireballs;
    private List<Inventory> inventory;

    public HeroStatus(int health, int fireballs, List<Inventory> inventory) {
        this.health = health;
        this.fireballs = fireballs;
        this.inventory = inventory;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getFireballs() {
        return fireballs;
    }

    public void setFireballs(int fireballs) {
        this.fireballs = fireballs;
    }

    public List<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(List<Inventory> inventory) {
        this.inventory = inventory;
    }
}