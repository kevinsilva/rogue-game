package pt.upskill.projeto1.objects.enemies;

import pt.upskill.projeto1.game.threads.EnemyMoveThread;
import pt.upskill.projeto1.game.GameManager;
import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.game.threads.EnemyTrapThread;
import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.objects.characters.Hero;
import pt.upskill.projeto1.objects.items.Trap;
import pt.upskill.projeto1.rogue.utils.*;

public class Enemy extends GameObject {
    private int health;
    private int power;
    private EnemyType type;
    private boolean trapped;
    private EnemyMoveThread enemyMoveThread;
    private EnemyTrapThread trapThread;

    public Enemy(Position position, EnemyType type) {
        super(position);
        this.type = type;
        this.health = type.getHealth();
        this.power = type.getPower();
        this.trapped = false;
        this.enemyMoveThread = null;
    }

    public void die() {
        StatusManager statusManager = StatusManager.getInstance();
        RoomManager roomManager = RoomManager.getInstance();
        GameManager.getInstance().updateScore(Points.DEFEAT_ENEMY.getPoints());

        statusManager.addMessage("Enemy died!");

        if (trapThread != null && trapThread.isAlive()) trapThread.stopThread();
        if (enemyMoveThread != null && enemyMoveThread.isAlive()) this.enemyMoveThread.stopThread();

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

        trapThread = new EnemyTrapThread(this);
        trapThread.start();
    }

    public void attack(Hero hero) {
        if (!isTrapped()) {
            hero.takeDamage(power);
        }
    }

    public void startEnemyThread() {
        if (enemyMoveThread != null && enemyMoveThread.isAlive()) return;
        this.enemyMoveThread = new EnemyMoveThread(this);
        this.enemyMoveThread.start();
    }

    public void move() {
        if (isTrapped() || GameManager.getInstance().getGameplayState() != GameplayState.GAME_ON) return;

        RoomManager roomManager = RoomManager.getInstance();

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

    public void setHealth(int health) { this.health = health; }

    public EnemyType getType() {
        return type;
    }

    public EnemyMoveThread getEnemyThread() {
        return enemyMoveThread;
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
    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
