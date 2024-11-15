package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.rogue.utils.Diagonal;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

public class Thief extends Enemy {

    public Thief(Position position, Room room) {
        super(position, room);
        setHealth(30);
        setPower(10);
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
    public void moveRandomly(Room currentRoom) {
        Diagonal[] directions = Diagonal.values();
        Diagonal randomDirection = directions[(int) (Math.random() * directions.length)];

        Position newPosition = this.getPosition().plus(randomDirection.asVector());

        if(currentRoom.isMoveValid(this, newPosition)) super.setPosition(newPosition);
    }

    @Override
    public String getName() {
        return "Thief";
    }

    @Override
    public void react(GameObject otherObject, RoomManager roomManager, StatusManager statusManager) {
        if (otherObject instanceof Hero) {
            Hero hero = (Hero) otherObject;
            steal(hero);
            attack(hero);
            statusManager.updateStatusBar();
        }
    }
}
