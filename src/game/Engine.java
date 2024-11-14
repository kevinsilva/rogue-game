package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.Hero;
import pt.upskill.projeto1.rogue.utils.Direction;

import java.awt.event.KeyEvent;

public class Engine {
    private final RoomManager roomManager;
    private final StatusManager statusManager;
    private final ImageMatrixGUI GUI;
    private final Hero hero;
    Direction previousDirection;

    public Engine(RoomManager roomManager, StatusManager statusManager) {
        this.roomManager = roomManager;
        this.statusManager = statusManager;
        this.GUI = ImageMatrixGUI.getInstance();
        this.hero = Hero.getInstance();
    }

    public void init(){
        GUI.setEngine(this);
        roomManager.updateGUI();
        GUI.go();

        while (true){
            GUI.update();
        }
    }

    public void notify(int keyPressed){
        Direction direction = null;

        if (keyPressed == KeyEvent.VK_DOWN){
            direction = Direction.DOWN;
            previousDirection = Direction.DOWN;
        }
        if (keyPressed == KeyEvent.VK_UP){
            direction = Direction.UP;
            previousDirection = Direction.UP;
        }
        if (keyPressed == KeyEvent.VK_LEFT){
            direction = Direction.LEFT;
            previousDirection = Direction.LEFT;
        }
        if (keyPressed == KeyEvent.VK_RIGHT){
            direction = Direction.RIGHT;
            previousDirection = Direction.RIGHT;
        }

        if(direction != null) hero.move(direction.asVector(), roomManager, statusManager);

        if (keyPressed == KeyEvent.VK_M) GUI.newImages(roomManager.getNextRoom().getGameObjects());
        if (keyPressed == KeyEvent.VK_N) GUI.newImages(roomManager.getPreviousRoom().getGameObjects());
        if (keyPressed == KeyEvent.VK_SPACE) hero.throwFireball(previousDirection, roomManager, statusManager);
        if (keyPressed == KeyEvent.VK_1) hero.removeItem(0, roomManager, statusManager);
        if (keyPressed == KeyEvent.VK_2) hero.removeItem(1, roomManager, statusManager);
        if (keyPressed == KeyEvent.VK_3) hero.removeItem(2, roomManager, statusManager);
    }
}
