package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public class Green extends InterfaceObject {
    private Position position;

    public Green(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Green";
    }
}
