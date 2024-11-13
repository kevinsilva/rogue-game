package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class FireOld extends GameObject implements FireTile {
    public FireOld(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Fire_old";
    }

    @Override
    public boolean validateImpact() {
        return false;
    }

    @Override
    public void react(GameObject otherObject, RoomManager roomManager, StatusManager statusManager) {

    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
}
