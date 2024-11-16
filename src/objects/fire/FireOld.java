package pt.upskill.projeto1.objects.fire;

import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.rogue.utils.Position;

public class FireOld extends GameObject implements FireTile {
    public FireOld(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Fire_old";
    }

    @Override
    public boolean validateImpact() {
        return false;
    }

    @Override
    public void react(GameObject otherObject) {

    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
}
