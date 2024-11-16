package pt.upskill.projeto1.objects.items;

import pt.upskill.projeto1.rogue.utils.Position;

public class Sword extends Inventory {
    public Sword(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Sword";
    }
}
