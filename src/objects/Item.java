package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.rogue.utils.Position;

public class Item extends GameObject {
    private String name;
    public Item(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Item";
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void react(GameObject otherObject, RoomManager roomManager, StatusManager statusManager) {

    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}
