package pt.upskill.projeto1.objects.ui;

import pt.upskill.projeto1.rogue.utils.Position;

public class Red extends InterfaceObject {
    private Position position;

    public Red(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Red";
    }
}
