package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.EnemyThread;
import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Room {
    private List<GameObject> gameObjects = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private HashMap<Integer, Door> doors = new HashMap<>();
    private Hero hero;
    private Key key;

    public Room() {
        this.init();
    }

    public void init() {
        for(int y=0; y<10; y++){
            for(int x=0; x<10; x++){
                this.addGameObject(new Floor(new Position(x, y)));
            }
        }
    }

    public void addGameObject(GameObject object) {
        this.gameObjects.add(object);
    }

    public void removeGameObjectAfterMs(GameObject object, int ms) {
        try {
        Thread.currentThread().sleep(ms);
        this.removeGameObject(object);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void giveBackGameObject(GameObject object) {
        addGameObject(object);
        ImageMatrixGUI.getInstance().newImages(this.getGameObjects());
    }

    public void addHero(Hero hero) {
        this.hero = hero;
        gameObjects.add(hero);
    }

    public Hero getHero() {
        return hero;
    }

    public void addDoor(int doorNumber, Door door) {
        doors.put(doorNumber, door);
    }

    public Door getDoor(int doorNumber) {
        return doors.get(doorNumber);
    }

    public void addKey(Key key) {
        this.key = key;
    }

    public Key getKey() {
        return key;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addEnemies() {
        for(Enemy enemy : enemies) {
            gameObjects.add(enemy);
        }
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void moveEnemies() {
        for (GameObject gameObject : gameObjects) {
            if(gameObject instanceof Enemy) {
                Enemy enemy = (Enemy) gameObject;
                EnemyThread thread = new EnemyThread(enemy);

                thread.start();
            }
        }
    }

    public void updateEnemies() {
        for (GameObject gameObject : gameObjects) {
            if(gameObject instanceof Enemy) {
                Enemy enemy = (Enemy) gameObject;
                EnemyThread enemyThread = new EnemyThread(enemy);

                enemyThread.start();
            }
        }
    }

    public void removeGameObject(GameObject object) {
        this.gameObjects.remove(object);
        ImageMatrixGUI.getInstance().removeImage(object);
        ImageMatrixGUI.getInstance().newImages(this.getGameObjects());
    }

    public List<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    public GameObject getGameObject(Position position) {
        int x = position.getX();
        int y = position.getY();
        GameObject gameObject = null;

        if (x < 0 || x > 9 || y < 0 || y > 9) return null;


        for (GameObject object : this.getGameObjects()) {
            int objectX = object.getPosition().getX();
            int objectY = object.getPosition().getY();
            if (objectX == x && objectY == y) {
                gameObject = object;
            }
        }
        return gameObject;
    }

    public Boolean isMoveValid(GameObject object, Position newPosition) {
        GameObject targetObject = this.getGameObject(newPosition);
        return targetObject != null && targetObject.isWalkable();
    }

    public void handleCollision(GameObject gameObject, GameObject otherObject, RoomManager roomManager, StatusManager statusManager) {
        gameObject.react(otherObject, roomManager, statusManager);
        otherObject.react(gameObject, roomManager, statusManager);
    }
}
