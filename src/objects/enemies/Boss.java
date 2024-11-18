package pt.upskill.projeto1.objects.enemies;

import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.rogue.utils.EnemyType;
import pt.upskill.projeto1.rogue.utils.Position;

public class Boss extends Enemy {
    public Boss(Position position) {
        super(position, EnemyType.BOSS);
    }

    @Override
    public void react(GameObject otherObject) {
        super.react(otherObject);
    }

    @Override
    public String getName() {
        return "Boss";
    }
}
