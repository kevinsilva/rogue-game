package pt.upskill.projeto1.objects.enemies;

import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.objects.fire.Fire;
import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.objects.hero.Hero;
import pt.upskill.projeto1.objects.items.Trap;
import pt.upskill.projeto1.rogue.utils.*;

public class Thief extends Enemy {

    public Thief(Position position) {
        super(position, EnemyType.THIEF);
    }

    public void steal(Hero hero) {
        hero.getStatus().getInventory().clear();
    }

    @Override
    public Vector2D directionTowards(Position target) {
        int x = target.getX() - getPosition().getX();
        int y = target.getY() - getPosition().getY();

        if (x != 0) x = x/Math.abs(x);
        if (y != 0) y = y/Math.abs(y);

        return new Vector2D(x, y);
    }

    @Override
    public void moveRandomly() {
        RoomManager roomManager = RoomManager.getInstance();
        StatusManager statusManager = StatusManager.getInstance();

        Diagonal[] directions = Diagonal.values();
        Diagonal randomDirection = directions[(int) (Math.random() * directions.length)];

        Position newPosition = this.getPosition().plus(randomDirection.asVector());
        GameObject otherObject = roomManager.getCurrentRoom().getGameObject(newPosition);

        if(roomManager.getCurrentRoom().isMoveValid(this, newPosition)) super.setPosition(newPosition);
        if (otherObject != null) roomManager.getCurrentRoom().handleCollision(this, otherObject);
    }

    @Override
    public String getName() {
        return "Thief";
    }

    @Override
    public void react(GameObject otherObject) {
        StatusManager statusManager = StatusManager.getInstance();

        if (otherObject instanceof Hero) {
            Hero hero = (Hero) otherObject;
            steal(hero);
            attack(hero);
            statusManager.updateStatusBar();
        }

        if (otherObject instanceof Trap) {
            getTrapped();
        }

        if (otherObject instanceof Fire) {
            takeDamage(Damage.FIREBALL.getDamage());
        }
    }
}
