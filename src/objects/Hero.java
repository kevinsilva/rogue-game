package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.rogue.utils.Constants;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.util.HashMap;

public class Hero extends GameObject   {
    private static final Hero INSTANCE = new Hero();
    private Position position;
    private HashMap<String, Item> items = new HashMap<>();

    private Hero() {
        super(new Constants().getINITIAL_POSITION());
    }

    public static Hero getInstance() { return INSTANCE; }

    public void move(Vector2D vector2D, RoomManager roomManager) {
        Position newPosition = this.getPosition().plus(vector2D);
        GameObject gameObject = roomManager.getCurrentRoom().getGameObject(newPosition.getX(), newPosition.getY());

        if (gameObject != null && gameObject.isWalkable()) {
            super.setPosition(newPosition);
            this.react(gameObject, roomManager);
            gameObject.react(this, roomManager);
        }

        if(gameObject instanceof DoorClosed) {
            this.react(gameObject, roomManager);
            gameObject.react(this, roomManager);
        }
    }

    public boolean hasItem(String itemName) {
        return this.items.containsKey(itemName);
    }


    @Override
    public String getName() {
        return "Hero";
    }

    @Override
    public void react(GameObject otherObject, RoomManager roomManager) {
        if (otherObject instanceof Key) {
            Key key = (Key) otherObject;
            items.put(key.getKeyId(), key);
            System.out.println("Collected a key!");
        }

    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
