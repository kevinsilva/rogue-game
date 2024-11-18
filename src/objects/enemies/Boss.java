package pt.upskill.projeto1.objects.enemies;

import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.rogue.utils.EnemyType;
import pt.upskill.projeto1.rogue.utils.Position;

public class Boss extends Enemy {
    public Boss(Position position, EnemyType type) {
        super(position, type);
    }

    @Override
    public void react(GameObject otherObject) {
        super.react(otherObject);
    }
}
