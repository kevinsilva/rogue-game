package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.Hero;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.awt.event.KeyEvent;

public class Engine {
    ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    Hero hero = Hero.getInstance();
    RoomManager rm = new RoomManager(hero);
    StatusManager sm = new StatusManager(rm);

    public void init(){
        rm.getCurrentRoom().addHero(hero);
        gui.setEngine(this);
        gui.newImages(rm.getCurrentRoom().getGameObjects());
        gui.go();

        while (true){
            gui.update();
        }
    }

    public void notify(int keyPressed){
        Hero hero = Hero.getInstance();
        Vector2D direction = null;

        if (keyPressed == KeyEvent.VK_DOWN){
            direction = Direction.DOWN.asVector();
        }
        if (keyPressed == KeyEvent.VK_UP){
            direction = Direction.UP.asVector();
        }
        if (keyPressed == KeyEvent.VK_LEFT){
            direction = Direction.LEFT.asVector();
        }
        if (keyPressed == KeyEvent.VK_RIGHT){
            direction = Direction.RIGHT.asVector();
        }

        if(direction != null) hero.move(direction, rm, sm);

        if (keyPressed == KeyEvent.VK_M) gui.newImages(rm.getNextRoom().getGameObjects());
        if (keyPressed == KeyEvent.VK_N) gui.newImages(rm.getPreviousRoom().getGameObjects());
        if (keyPressed == KeyEvent.VK_E) sm.removeHealth();
        if (keyPressed == KeyEvent.VK_F) sm.removeFireball();
        if (keyPressed == KeyEvent.VK_SPACE) hero.throwFireball(rm, sm);
        if (keyPressed == KeyEvent.VK_1) hero.removeItem(0, rm, sm);
        if (keyPressed == KeyEvent.VK_2) hero.removeItem(1, rm, sm);
        if (keyPressed == KeyEvent.VK_3) hero.removeItem(2, rm, sm);
    }

    public static void main(String[] args){
        Engine engine = new Engine();
        engine.init();
    }

}
