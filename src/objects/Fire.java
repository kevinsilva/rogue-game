package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.rogue.utils.Position;

public class Fire extends GameObject implements FireTile {
    RoomManager roomManager;
    public Fire(Position position, RoomManager roomManager) {
        super(position);
        this.roomManager = roomManager;
    }

    @Override
    public String getName() {
        return "Fire";
    }

    @Override
    public boolean validateImpact() {
        int nextX = this.getPosition().getX() + 1;
        int nextY = this.getPosition().getY();
        GameObject otherObject = roomManager.getCurrentRoom().getGameObject(nextX, nextY);

        if (!(otherObject.isWalkable())) {
            FireOld fireold = new FireOld(this.getPosition());

            roomManager.getCurrentRoom().removeGameObject(this);
            roomManager.getCurrentRoom().addGameObject(fireold);
            roomManager.updateGUI();
            roomManager.getCurrentRoom().removeGameObjectAfterMs(fireold, 150);

            return false;
        }
        return true;
    }

    @Override
    public void react(GameObject otherObject, RoomManager roomManager, StatusManager statusManager) {
    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
