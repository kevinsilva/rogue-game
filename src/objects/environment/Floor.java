package pt.upskill.projeto1.objects.environment;

import pt.upskill.projeto1.objects.GameObject;
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
    public void react(GameObject otherObject) {}

    @Override
    public boolean isWalkable() {
        return true;
    }
}
