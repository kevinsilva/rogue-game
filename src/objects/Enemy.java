package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.rogue.utils.Position;

public class Enemy extends GameObject {
    public Enemy(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Enemy";
    }

    @Override
    public void react(GameObject otherObject, RoomManager roomManager, StatusManager statusManager) {
        System.out.println("Enemy");
    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
