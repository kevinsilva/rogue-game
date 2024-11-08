package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public class Item extends GameObject {
    public Item(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Item";
    }

    @Override
    public void react(GameObject otherObject) {
        System.out.println("Item");
    }
}
