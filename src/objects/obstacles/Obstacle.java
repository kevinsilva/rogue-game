package pt.upskill.projeto1.objects.obstacles;

import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.rogue.utils.Position;

public abstract class Obstacle extends GameObject {
    public Obstacle(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Obstacle";
    }

    @Override
    public void react(GameObject otherObject) {
        System.out.println("Obstacle");
    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
