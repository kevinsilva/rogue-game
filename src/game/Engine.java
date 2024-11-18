package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.characters.Hero;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.GameplayState;

import java.awt.event.KeyEvent;

public class Engine {
    private final GameManager gameManager = GameManager.getInstance();
    private final RoomManager roomManager = RoomManager.getInstance();
    private final StatusManager statusManager = StatusManager.getInstance();
    private final ImageMatrixGUI GUI = ImageMatrixGUI.getInstance();
    private final Hero hero = Hero.getInstance();
    Direction previousDirection;

    public void init(){
        GUI.setEngine(this);
        roomManager.updateGUI();
        GUI.go();

        while (true){
            GameplayState currentState = gameManager.getGameplayState();

            if (currentState == GameplayState.GAME_OVER) {
                System.out.println("Game Over! Exiting game loop.");
                roomManager.getCurrentRoom().removeGameObject(hero);
                roomManager.getCurrentRoom().stopEnemies();
                GUI.showMessage("GO","Game Over! Press R to restart or Q to quit.");
                break;
            }

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

        if(direction != null) {
            hero.move(direction.asVector());
        };

        if (keyPressed == KeyEvent.VK_M) GUI.newImages(roomManager.getNextRoom().getGameObjects());
        if (keyPressed == KeyEvent.VK_N) GUI.newImages(roomManager.getPreviousRoom().getGameObjects());
        if (keyPressed == KeyEvent.VK_SPACE) hero.throwFireball(previousDirection);
        if (keyPressed == KeyEvent.VK_1) hero.removeItem(0);
        if (keyPressed == KeyEvent.VK_2) hero.removeItem(1);
        if (keyPressed == KeyEvent.VK_3) hero.removeItem(2);
        if (keyPressed == KeyEvent.VK_T) hero.setTrap();
        if (keyPressed == KeyEvent.VK_S) GameManager.getInstance().saveGame();
        if (keyPressed == KeyEvent.VK_L) GameManager.getInstance().loadGame();
    }
}
