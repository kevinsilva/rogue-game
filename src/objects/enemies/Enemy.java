package pt.upskill.projeto1.objects.enemies;

import pt.upskill.projeto1.game.EnemyThread;
import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.objects.hero.Hero;
import pt.upskill.projeto1.objects.items.Trap;
import pt.upskill.projeto1.rogue.utils.*;

public class Enemy extends GameObject {
    private int health;
    private int power;
    private EnemyType type;
    private boolean trapped;
    private EnemyThread enemyThread;

    public Enemy(Position position, EnemyType type) {
        super(position);
        this.type = type;
        this.health = type.getHealth();
        this.power = type.getPower();
        this.trapped = false;
        this.enemyThread = null;
    }

    public void die() {
        RoomManager roomManager = RoomManager.getInstance();

        if (enemyThread != null && enemyThread.isAlive()) this.enemyThread.stopThread();
        roomManager.getCurrentRoom().getEnemies().remove(this);
        roomManager.getCurrentRoom().removeGameObject(this);
    }

    public void takeDamage(int damage) {
        setHealth(getHealth() - damage);
        if (getHealth() <= 0) die();
    }

    public void setTrapped(boolean trapped) {
        this.trapped = trapped;
    }

    public boolean isTrapped() {
        return trapped;
    }

    public void getTrapped() {
        if(trapped) return;
        setTrapped(true);

        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } finally {
                setTrapped(false);
            }
        }).start();
    }

    public void attack(Hero hero) {
        if (!isTrapped()) {
            hero.takeDamage(power);
        }
    }

    public void startEnemyThread() {
        if (enemyThread != null && enemyThread.isAlive()) return;
        this.enemyThread = new EnemyThread(this);
        this.enemyThread.start();
    }

    public void move() {
        if (isTrapped()) return;

        RoomManager roomManager = RoomManager.getInstance();
        StatusManager statusManager = StatusManager.getInstance();

        Position heroPosition = roomManager.getCurrentRoom().getHero().getPosition();
        Vector2D heroVector = new Vector2D(heroPosition.getX(), heroPosition.getY());
        Vector2D thisVector = new Vector2D(getPosition().getX(), getPosition().getY());
        Vector2D difference = heroVector.minus(thisVector);

        if (difference.getX() <= Constants.ENEMY_ALERT_RANGE && difference.getY() <= Constants.ENEMY_ALERT_RANGE) {
            Vector2D direction = directionTowards(heroPosition);
            Position newPosition = getPosition().plus(direction);
            GameObject otherObject = roomManager.getCurrentRoom().getGameObject(newPosition);

            if(roomManager.getCurrentRoom().isMoveValid(this,newPosition)) setPosition(newPosition);
            if (otherObject != null) roomManager.getCurrentRoom().handleCollision(this, otherObject);

        } else {
            moveRandomly();
        }
    }

    public Vector2D directionTowards(Position target) {
        int xDiff = target.getX() - getPosition().getX();
        int yDiff = target.getY() - getPosition().getY();

        if (xDiff != 0) xDiff = xDiff/Math.abs(xDiff);
        if (yDiff != 0) yDiff = yDiff /Math.abs(yDiff);

        if (Math.abs(xDiff) != 0 && Math.abs(yDiff) != 0) {
            int random = (int) (Math.random() * 2);
            if (random == 0) xDiff = 0;
            else yDiff = 0;
        }

        return new Vector2D(xDiff, yDiff);
    }

    public void moveRandomly() {
        RoomManager roomManager = RoomManager.getInstance();
        StatusManager statusManager = StatusManager.getInstance();

        Direction[] directions = Direction.values();
        Direction randomDirection = directions[(int) (Math.random() * directions.length)];
        Position newPosition = this.getPosition().plus(randomDirection.asVector());

        GameObject otherObject = roomManager.getCurrentRoom().getGameObject(newPosition);

        if(roomManager.getCurrentRoom().isMoveValid(this, newPosition)) super.setPosition(newPosition);
        if (otherObject != null) roomManager.getCurrentRoom().handleCollision(this, otherObject);
    }

    public int getHealth() { return health; }

    public int getPower() { return power; }

    public void setHealth(int health) { this.health = health; }

    public void setPower(int power) { this.power = power; }

    public EnemyType getType() {
        return type;
    }

    public EnemyThread getEnemyThread() {
        return enemyThread;
    }

    @Override
    public String getName() {
        return "Enemy";
    }

    @Override
    public void react(GameObject otherObject) {
        RoomManager roomManager = RoomManager.getInstance();
        StatusManager statusManager = StatusManager.getInstance();

        if (otherObject instanceof Hero) {
            Hero hero = (Hero) otherObject;
            attack(hero);
            statusManager.updateStatusBar();
        }

        if (otherObject instanceof Trap) {
            getTrapped();
        }
    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
