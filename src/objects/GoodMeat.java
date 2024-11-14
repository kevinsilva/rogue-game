package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.rogue.utils.Position;

public class GoodMeat extends Item {
    public GoodMeat(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "GoodMeat";
    }

    @Override
    public void react(GameObject otherObject, RoomManager roomManager, StatusManager statusManager) {
        if(otherObject instanceof Hero) {
            statusManager.addHealth();
            statusManager.updateStatusBar();
            roomManager.getCurrentRoom().removeGameObject(this);
        }
    }
}
