package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Constants;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

public class Hero extends GameObject   {
    private static final Hero INSTANCE = new Hero();
    private Position position;

    private Hero() {
        super(new Constants().getINITIAL_POSITION());
    }

    public static Hero getInstance() { return INSTANCE; }

    public void move(Vector2D vector2D) {
        super.setPosition(this.getPosition().plus(vector2D));
    }

    @Override
    public String getName() {
        return "Hero";
    }

    @Override
    public void react(GameObject otherObject) {
        System.out.println("Hero");
    }
}
