package pt.upskill.projeto1.objects.enemies;

import pt.upskill.projeto1.rogue.utils.EnemyType;
import pt.upskill.projeto1.rogue.utils.Position;

public class Prisoner extends Enemy {

    public Prisoner(Position position) {
        super(position, EnemyType.PRISONER);
    }

    @Override
    public String getName() {
        return "Prisoner";
    }
}
