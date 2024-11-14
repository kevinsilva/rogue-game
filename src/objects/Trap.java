package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public class Trap extends Inventory {
    public Trap(Position position){
        super(position);
    }

    @Override
    public String getName() {
        return "Trap";
    }
}
