package pt.upskill.projeto1.objects.enemies;

import pt.upskill.projeto1.rogue.utils.EnemyType;
import pt.upskill.projeto1.rogue.utils.Position;

public class BadGuy extends Enemy {

    public BadGuy(Position position) {
        super(position, EnemyType.BADGUY);
    }

    @Override
    public String getName() {
        return "BadGuy";
    }
}
