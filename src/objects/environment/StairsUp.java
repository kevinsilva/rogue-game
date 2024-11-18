package pt.upskill.projeto1.objects.environment;

import pt.upskill.projeto1.game.GameManager;
import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.objects.characters.Hero;
import pt.upskill.projeto1.rogue.utils.Points;
import pt.upskill.projeto1.rogue.utils.Position;

public class StairsUp extends GameObject {
    public StairsUp(Position position) {
        super(position);
    }

    @Override
    public void react(GameObject otherObject) {
        RoomManager roomManager = RoomManager.getInstance();

        if(otherObject instanceof Hero) {
            GameManager.getInstance().updateScore(Points.PASS_ROOM.getPoints());
            roomManager.getNextRoom();
        }
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public String getName() {
        return "StairsUp";
    }
}
