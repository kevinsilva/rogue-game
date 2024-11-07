package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

public class Hero implements ImageTile {
    private static final Hero INSTANCE = new Hero();
    private Position position;

    private Hero() {
        this.position = new Position(0, 0);
    }

    public static Hero getInstance() { return INSTANCE; }

    public void move(Vector2D vector2D) {
        this.setPosition(this.getPosition().plus(vector2D));
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "Hero";
    }

    @Override
    public Position getPosition() {
        return position;
    }

}
