package pt.upskill.projeto1.rogue.utils.data;

import pt.upskill.projeto1.rogue.utils.EnemyType;
import pt.upskill.projeto1.rogue.utils.Position;

import java.io.Serializable;

public class EnemyData implements Serializable {
    private Position position;
    private int health;
    private EnemyType type;

    public EnemyData(Position position, int health, EnemyType type) {
        this.position = position;
        this.health = health;
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public EnemyType getType() {
        return type;
    }

    public void setType(EnemyType type) {
        this.type = type;
    }
}
