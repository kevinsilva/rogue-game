package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;

public class Fire extends GameObject implements FireTile {
    RoomManager roomManager;
    Direction direction;
    public Fire(Position position, Direction direction,RoomManager roomManager) {
        super(position);
        this.roomManager = roomManager;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String getName() {
        return "Fire";
    }

    @Override
    public boolean validateImpact() {
        Position nextPosition = this.getPosition().plus(this.getDirection().asVector());
        GameObject otherObject = roomManager.getCurrentRoom().getGameObject(nextPosition);

        if (!(otherObject.isWalkable())) {
            FireOld fireold = new FireOld(this.getPosition());

            roomManager.getCurrentRoom().removeGameObject(this);
            roomManager.getCurrentRoom().addGameObject(fireold);
            roomManager.updateGUI();

            roomManager.getCurrentRoom().removeGameObjectAfterMs(fireold, 250);

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
