package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.FireBallThread;
import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Constants;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Hero extends GameObject   {
    Constants CONSTANTS = new Constants();
    private static final Hero INSTANCE = new Hero();
    private HashMap<String, Item> keys = new HashMap<>();
    private int health = CONSTANTS.getINITIAL_HEALTH();
    private HeroStatus status = new HeroStatus(health, CONSTANTS.getINITIAL_FIREBALLS(), CONSTANTS.getINITIAL_INVENTORY());
    private Position lastPosition;

    private Hero() {
        super(new Constants().getINITIAL_POSITION());
    }

    public static Hero getInstance() { return INSTANCE; }

    public void takeDamage(int damage) {
        setHealth(getHealth() - damage);
        status.setHealth(health);

        if(health <= 0) System.out.println("dead");
    }

    public int calculateTotalDamage() {
        int swords = 0;
        int hammers = 0;

        for (Inventory item : status.getInventory()) {
            if (item instanceof Sword) swords++;
            if (item instanceof Hammer) hammers++;
        }

        int swordDamage = CONSTANTS.getSWORD_DAMAGE() * swords;
        int hammerDamage = CONSTANTS.getHAMMER_DAMAGE() * hammers;

        return CONSTANTS.getUNARMED_DAMAGE() + swordDamage + hammerDamage;
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

    public boolean hasKey(String keyName) {
        return this.keys.containsKey(keyName);
    }

    public boolean hasItem(String itemName) {
        return status.getInventory().contains(itemName);
    }

    public void removeItem(int index, RoomManager roomManager, StatusManager statusManager) {
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

    public void addItem(Item item, RoomManager roomManager, StatusManager statusManager) {
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

    public void throwFireball(Direction direction, RoomManager roomManager, StatusManager statusManager) {
        if(status.getFireballs() > 0) {
            status.setFireballs(status.getFireballs() - 1);
            statusManager.updateStatusBar();

            Fire fire = new Fire(this.getPosition().plus(Objects.requireNonNull(direction.asVector())), direction, roomManager);
            FireBallThread thread = new FireBallThread(direction, fire);

            roomManager.getCurrentRoom().addGameObject(fire);
            roomManager.updateGUI();
            thread.start();

        }
    }

    public void move(Vector2D vector2D, RoomManager roomManager, StatusManager statusManager) {
        lastPosition = this.getPosition();
        Position newPosition = this.getPosition().plus(vector2D);
        GameObject otherObject = roomManager.getCurrentRoom().getGameObject(newPosition);
        Room currentRoom = roomManager.getCurrentRoom();

        if (currentRoom.isMoveValid(this, newPosition) && !(otherObject instanceof DoorClosed)) super.setPosition(newPosition);
        if (otherObject != null) currentRoom.handleCollision(this, otherObject, roomManager, statusManager);
    }


    @Override
    public String getName() {
        return "Hero";
    }

    @Override
    public void react(GameObject otherObject, RoomManager roomManager, StatusManager statusManager) {
        if (otherObject instanceof Key) {
            Key key = (Key) otherObject;
            keys.put(key.getKeyId(), key);
            roomManager.getCurrentRoom().removeGameObject(key);
        }

        if (otherObject instanceof Inventory) {
            Inventory item = (Inventory) otherObject;
            addItem(item, roomManager, statusManager);
        }

        if (otherObject instanceof Enemy) {
            Enemy enemy = (Enemy) otherObject;
        }

    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
