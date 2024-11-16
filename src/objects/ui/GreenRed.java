package pt.upskill.projeto1.objects.ui;

import pt.upskill.projeto1.rogue.utils.Position;

public class GreenRed extends InterfaceObject {
    private Position position;

    public GreenRed(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "GreenRed";
    }
}
