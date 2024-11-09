package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Floor extends GameObject {
    public Floor(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Floor";
    }

    @Override
    public void react(GameObject otherObject, RoomManager roomManager) {}

    @Override
    public boolean isWalkable() {
        return true;
    }
}
