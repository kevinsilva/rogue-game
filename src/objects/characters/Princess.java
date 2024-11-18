package pt.upskill.projeto1.objects.characters;

import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.rogue.utils.Position;

public class Princess extends GameObject {
    public Princess(Position position) {
        super(position);
    }

    @Override
    public void react(GameObject otherObject) {

    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public String getName() {
        return "Princess";
    }
}
