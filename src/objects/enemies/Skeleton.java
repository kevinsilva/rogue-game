package pt.upskill.projeto1.objects.enemies;

import pt.upskill.projeto1.rogue.utils.EnemyType;
import pt.upskill.projeto1.rogue.utils.Position;

public class Skeleton extends Enemy {
    public Skeleton(Position position) {
        super(position, EnemyType.SKELETON);
    }

    @Override
    public String getName() {
        return "Skeleton";
    }
}
