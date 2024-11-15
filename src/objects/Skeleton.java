package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public class Skeleton extends Enemy {
    public Skeleton(Position position, Room room) {
        super(position, room);
        this.setHealth(50);
        setPower(10);
    }

    @Override
    public String getName() {
        return "Skeleton";
    }
}
