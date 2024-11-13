package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Room {
    private List<GameObject> gameObjects = new ArrayList<>();
    private HashMap<Integer, Door> doors = new HashMap<>();
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
        Thread.sleep(ms);
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
        gameObjects.add(hero);
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

    public void removeGameObject(GameObject object) {
        this.gameObjects.remove(object);
        ImageMatrixGUI.getInstance().newImages(this.getGameObjects());
    }

    public List<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    public GameObject getGameObject(int x, int y) {
        if(x < 0 || x > 9 && y < 0 || y > 9) return null;
        GameObject gameObject = null;

        for(GameObject object : this.getGameObjects()) {
            int objectX = object.getPosition().getX();
            int objectY = object.getPosition().getY();
            if(objectX == x && objectY == y) {
                gameObject = object;
            }
        }
        return gameObject;
    }
}
