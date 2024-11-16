package pt.upskill.projeto1.objects.items;

import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.rogue.utils.Position;

public class Item extends GameObject {
    private String name;
    public Item(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Item";
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void react(GameObject otherObject) {

    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}
