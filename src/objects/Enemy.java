package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.rogue.utils.Constants;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

public class Enemy extends GameObject {
    Constants CONSTANTS = new Constants();
    private Room room;
    private boolean trapped;
    private int health;
    private int power;

    public Enemy(Position position, Room room) {
        super(position);
        trapped = false;
        this.room = room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) System.out.println("enemy dead");
    }

    public boolean isTrapped() {
        return trapped;
    }

    private void getTrapped() {
        trapped = true;

        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            trapped = false;
        }).start();
    }

    public void attack(Hero hero) {
        if (!isTrapped()) {
            hero.takeDamage(power);
        }
    }

    public void move() {
        if (isTrapped()) return;
        Position heroPosition = room.getHero().getPosition();
        Vector2D heroVector = new Vector2D(heroPosition.getX(), heroPosition.getY());
        Vector2D thisVector = new Vector2D(getPosition().getX(), getPosition().getY());
        Vector2D difference = heroVector.minus(thisVector);

        if (difference.getX() <= CONSTANTS.ENEMY_ALERT_RANGE() && difference.getY() <= CONSTANTS.ENEMY_ALERT_RANGE()) {
            Vector2D direction = directionTowards(heroPosition);
            Position newPosition = getPosition().plus(direction);

            if(room.isMoveValid(this,newPosition)) setPosition(newPosition);
        } else {
            moveRandomly(room);
        }
    }

    public Vector2D directionTowards(Position target) {
        int x = target.getX() - getPosition().getX();
        int y = target.getY() - getPosition().getY();

        if (x != 0) x = x/Math.abs(x);
        if (y != 0) y = y/Math.abs(y);

        if (Math.abs(x) > 0 && Math.abs(y) > 0) {
            int random = (int) (Math.random() * 2);
            if (random == 0) x = 0;
            else y = 0;
        }

        return new Vector2D(x, y);
    }

    public void moveRandomly(Room currentRoom) {
        Direction[] directions = Direction.values();
        Direction randomDirection = directions[(int) (Math.random() * directions.length)];

        Position newPosition = this.getPosition().plus(randomDirection.asVector());

        if(currentRoom.isMoveValid(this, newPosition)) super.setPosition(newPosition);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String getName() {
        return "Enemy";
    }

    @Override
    public void react(GameObject otherObject, RoomManager roomManager, StatusManager statusManager) {
        if (otherObject instanceof Hero) {
            Hero hero = (Hero) otherObject;
            attack(hero);
            statusManager.updateStatusBar();
        }
    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
