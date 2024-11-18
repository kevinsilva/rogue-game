package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.enemies.Enemy;
import pt.upskill.projeto1.objects.environment.Door;
import pt.upskill.projeto1.objects.environment.Floor;
import pt.upskill.projeto1.objects.characters.Hero;
import pt.upskill.projeto1.objects.items.Key;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room {
    ImageMatrixGUI GUI = ImageMatrixGUI.getInstance();
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

    public void removeGameObject(GameObject object) {
        this.gameObjects.remove(object);
        updateGUI();
    }

    public void removeGameObjectAfterMs(GameObject object, int ms) {
        try {
        Thread.sleep(ms);
        this.removeGameObject(object);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public void giveBackGameObject(GameObject object) {
        addGameObject(object);
        updateGUI();
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

    public void addEnemiesToGameObjects() {
        for(Enemy enemy : enemies) {
            gameObjects.add(enemy);
        };

        moveEnemies();
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void moveEnemies() {
        for (GameObject gameObject : gameObjects) {
            if(gameObject instanceof Enemy) {
                Enemy enemy = (Enemy) gameObject;
                enemy.startEnemyThread();
            }
        }
        updateGUI();
    }

    public void stopEnemies() {
        for (GameObject gameObject : gameObjects) {
            if(gameObject instanceof Enemy) {
                Enemy enemy = (Enemy) gameObject;
                enemy.getEnemyThread().stopThread();
            }
        }
    }

    public void clearEnemies() {
        stopEnemies();
        for (Enemy enemy : enemies) {
            removeGameObject(enemy);
            this.gameObjects.remove(enemy);
            GUI.removeImage(enemy);
        }
        getEnemies().clear();
        updateGUI();
    }

    public Boolean isMoveValid(GameObject object, Position newPosition) {
        GameObject targetObject = this.getGameObject(newPosition);
        return targetObject != null && targetObject.isWalkable();
    }

    public void handleCollision(GameObject gameObject, GameObject otherObject) {
        gameObject.react(otherObject);
        otherObject.react(gameObject);
    }

    public void updateGUI() {
        this.GUI.newImages(getGameObjects());
    }
}
