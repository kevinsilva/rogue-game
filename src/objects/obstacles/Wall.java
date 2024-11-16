package pt.upskill.projeto1.objects.obstacles;

import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.rogue.utils.Position;

public class Wall extends GameObject {
    public Wall(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Wall";
    }

    @Override
    public void react(GameObject otherObject) {

    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}


