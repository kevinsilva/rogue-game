package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public class BadGuy extends Enemy {

    public BadGuy(Position position, Room room) {
        super(position, room);
        this.setHealth(75);
        this.setPower(25);
    }

    @Override
    public String getName() {
        return "BadGuy";
    }
}
