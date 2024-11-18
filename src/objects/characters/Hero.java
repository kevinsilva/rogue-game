package pt.upskill.projeto1.objects.characters;

import pt.upskill.projeto1.game.threads.FireBallThread;
import pt.upskill.projeto1.game.GameManager;
import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.objects.Room;
import pt.upskill.projeto1.objects.enemies.Enemy;
import pt.upskill.projeto1.objects.fire.Fire;
import pt.upskill.projeto1.objects.items.*;
import pt.upskill.projeto1.objects.obstacles.DoorClosed;
import pt.upskill.projeto1.rogue.utils.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Hero extends GameObject {
    private static final Hero INSTANCE = new Hero();
    private HashMap<String, Item> keys = new HashMap<>();
    private int health = Constants.INITIAL_HEALTH;
    private HeroStatus status = new HeroStatus(health, Constants.INITIAL_FIREBALLS, Constants.INITIAL_INVENTORY);
    private Position lastPosition;

    private Hero() {
        super(Constants.INITIAL_POSITION);
    }

    public static Hero getInstance() { return INSTANCE; }

    public void takeDamage(int damage) {
        setHealth(getHealth() - damage);
        status.setHealth(health);

        if(health <= 0) GameManager.getInstance().setGameplayState(GameplayState.GAME_OVER);
    }

    public int calculateTotalDamage() {
        int armedDamage = 0;

        for (Inventory item : status.getInventory()) {
            if (item instanceof Sword) armedDamage += Damage.SWORD.getDamage();
            else if (item instanceof Hammer) armedDamage += Damage.HAMMER.getDamage();
        }

        return armedDamage == 0 ? Damage.UNARMED.getDamage() : armedDamage;
    }

    public void attack(Enemy enemy) {
        enemy.takeDamage(calculateTotalDamage());
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public HeroStatus getStatus() {
        return status;
    }

    public HashMap<String, Item> getKeys() {
        return keys;
    }

    public void setKeys(HashMap<String, Item> keys) {
        this.keys = keys;
    }

    public boolean hasKey(String keyName) {
        return this.keys.containsKey(keyName);
    }

    public boolean hasItem(String itemName) {
        return status.getInventory().contains(itemName);
    }

    public void removeItem(int index) {
        RoomManager roomManager = RoomManager.getInstance();
        StatusManager statusManager = StatusManager.getInstance();

        ImageTile lastTile = roomManager.getCurrentRoom().getGameObject(lastPosition);
        List<Inventory> inventory = status.getInventory();

        if (!inventory.isEmpty() && !(lastTile instanceof Inventory)) {
            Inventory item = inventory.get(index);
            if (!(item instanceof EmptyInventory)) {
                inventory.set(index, new EmptyInventory(null));
                item.setPosition(lastPosition);
                roomManager.getCurrentRoom().giveBackGameObject(item);
                statusManager.updateStatusBar();
            }
        }
    }

    public void addItem(Item item) {
        if(item instanceof Trap && ((Trap) item).getIsArmed()) return;
        RoomManager roomManager = RoomManager.getInstance();
        StatusManager statusManager = StatusManager.getInstance();

        List<Inventory> inventory = status.getInventory();

        if (!inventory.isEmpty()) {
            for (int i = 0; i < inventory.size(); i++) {
                Inventory inventoryItem = inventory.get(i);
                if (inventoryItem instanceof EmptyInventory) {
                    inventory.set(i, (Inventory) item);
                    roomManager.getCurrentRoom().removeGameObject(item);
                    statusManager.updateStatusBar();
                    break;
                }
            }
        }
    }

    public void throwFireball(Direction direction) {
        RoomManager roomManager = RoomManager.getInstance();
        StatusManager statusManager = StatusManager.getInstance();

        if(status.getFireballs() > 0) {
            status.setFireballs(status.getFireballs() - 1);
            statusManager.updateStatusBar();

            Fire fire = new Fire(this.getPosition().plus(Objects.requireNonNull(direction.asVector())), direction);
            FireBallThread thread = new FireBallThread(direction, fire);

            roomManager.getCurrentRoom().addGameObject(fire);
            roomManager.updateGUI();
            thread.start();

        }
    }

    public void setTrap() {
        RoomManager roomManager = RoomManager.getInstance();
        StatusManager statusManager = StatusManager.getInstance();

        List<Inventory> inventory = status.getInventory();

        for (int i = 0; i < inventory.size(); i++) {
            Inventory item = inventory.get(i);
            if (item instanceof Trap) {
                Trap trap = (Trap) item;
                inventory.set(i, new EmptyInventory(null));
                trap.setPosition(lastPosition);
                trap.setIsArmed();
                roomManager.getCurrentRoom().giveBackGameObject(item);
                roomManager.getCurrentRoom().addHero(this);
                roomManager.getCurrentRoom().addEnemiesToGameObjects();
                roomManager.updateGUI();
                statusManager.updateStatusBar();
            }
        }
    }

    public void move(Vector2D vector2D) {
        GameManager.getInstance().updateScore(Points.MOVE.getPoints());

        RoomManager roomManager = RoomManager.getInstance();
        StatusManager statusManager = StatusManager.getInstance();

        lastPosition = this.getPosition();
        Position newPosition = this.getPosition().plus(vector2D);
        GameObject otherObject = roomManager.getCurrentRoom().getGameObject(newPosition);
        Room currentRoom = roomManager.getCurrentRoom();

        if (currentRoom.isMoveValid(this, newPosition) && !(otherObject instanceof DoorClosed)) super.setPosition(newPosition);
        if (otherObject != null) currentRoom.handleCollision(this, otherObject);
    }


    @Override
    public String getName() {
        return "Hero";
    }

    @Override
    public void react(GameObject otherObject) {
        RoomManager roomManager = RoomManager.getInstance();
        StatusManager statusManager = StatusManager.getInstance();

        if (otherObject instanceof Key) {
            statusManager.addMessage("Picked up a Key");
            GameManager.getInstance().updateScore(Points.COLLECT_KEY.getPoints());
            Key key = (Key) otherObject;
            keys.put(key.getKeyId(), key);
            roomManager.getCurrentRoom().removeGameObject(key);
        }

        if (otherObject instanceof Inventory) {
            statusManager.addMessage("Picked up an Item");
            GameManager.getInstance().updateScore(Points.COLLECT_ITEM.getPoints());
            Inventory item = (Inventory) otherObject;
            addItem(item);
        }

        if (otherObject instanceof Enemy) {
            statusManager.addMessage("Watch out the enemy!");
            Enemy enemy = (Enemy) otherObject;
            attack(enemy);
        }

    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
