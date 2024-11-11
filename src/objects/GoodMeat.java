package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public class GoodMeat extends Item {
    public GoodMeat(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "GoodMeat";
    }
}
