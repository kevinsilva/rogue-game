package pt.upskill.projeto1.objects.ui;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

public abstract class InterfaceObject implements ImageTile {
    protected Position position;

    public InterfaceObject(Position position) {
        this.position = position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public abstract String getName();
}
