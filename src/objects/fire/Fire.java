package pt.upskill.projeto1.objects.fire;

import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.objects.environment.Door;
import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.objects.enemies.Enemy;
import pt.upskill.projeto1.rogue.utils.Damage;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;

public class Fire extends GameObject implements FireTile {
    RoomManager roomManager;
    Direction direction;
    public Fire(Position position, Direction direction) {
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
        RoomManager roomManager = RoomManager.getInstance();

        Position nextPosition = this.getPosition().plus(this.getDirection().asVector());
        GameObject otherObject = roomManager.getCurrentRoom().getGameObject(nextPosition);

        if ((otherObject instanceof Door) || !(otherObject.isWalkable())) {
            FireOld fireold = new FireOld(this.getPosition());
            this.react(otherObject);

            roomManager.getCurrentRoom().removeGameObject(this);
            roomManager.getCurrentRoom().addGameObject(fireold);
            roomManager.updateGUI();

            roomManager.getCurrentRoom().removeGameObjectAfterMs(fireold, 500);

            return false;
        }
        return true;
    }

    @Override
    public void react(GameObject otherObject) {
        if (otherObject instanceof Enemy enemy) {
            enemy.takeDamage(Damage.FIREBALL.getDamage());
        }
    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
