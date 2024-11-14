package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public class Black extends InterfaceObject {
    private Position position;

    public Black(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Black";
    }
}
