package pt.upskill.projeto1.game.state;

import pt.upskill.projeto1.objects.items.Inventory;
import pt.upskill.projeto1.objects.items.Item;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.data.InventoryData;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class HeroState implements Serializable {
    private int health;
    private int fireballs;
    private Position position;
    private List<InventoryData> inventory;
    private HashMap<String, Item> keys;

    public HeroState(int health, int fireballs, Position position, List<InventoryData> inventory, HashMap<String, Item> keys) {
        this.health = health;
        this.fireballs = fireballs;
        this.position = position;
        this.inventory = inventory;
        this.keys = keys;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<InventoryData> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryData> inventory) {
        this.inventory = inventory;
    }

    public HashMap<String, Item> getKeys() {
        return keys;
    }

    public void setKeys(HashMap<String, Item> keys) {
        this.keys = keys;
    }
}
