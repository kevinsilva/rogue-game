package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public class Hammer extends Inventory {
    public Hammer(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Hammer";
    }
}
