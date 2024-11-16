package pt.upskill.projeto1.objects.items;

import pt.upskill.projeto1.rogue.utils.Position;

public class EmptyInventory extends Inventory {
    public EmptyInventory(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Empty Inventory";
    }
}
