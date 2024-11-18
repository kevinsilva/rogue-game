package pt.upskill.projeto1.objects.enemies;

import pt.upskill.projeto1.rogue.utils.EnemyType;
import pt.upskill.projeto1.rogue.utils.Position;

public class EvilBat extends Enemy {
    public EvilBat(Position position) {
        super(position, EnemyType.EVILBAT);
    }

    @Override
    public String getName() {
        return "EvilBat";
    }
}
