package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.Hero;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.awt.event.KeyEvent;

public class Engine {
    ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    Hero hero = Hero.getInstance();
    RoomManager rm = new RoomManager(hero);

    public void init(){
        rm.getCurrentRoom().addHero(hero);
        gui.setEngine(this);
        gui.newImages(rm.getCurrentRoom().getTiles());
        gui.go();

        gui.setStatus("O jogo começou!");

        while (true){
            gui.update();
        }
    }

    public void notify(int keyPressed){
        Hero hero = Hero.getInstance();
        Position position = hero.getPosition();
        if (keyPressed == KeyEvent.VK_DOWN){
            Vector2D direction = Direction.DOWN.asVector();
            if(rm.getCurrentRoom().isTileMoveable(position.plus(direction))) hero.move(direction);
        }
        if (keyPressed == KeyEvent.VK_UP){
            Vector2D direction = Direction.UP.asVector();
            if(rm.getCurrentRoom().isTileMoveable(position.plus(direction))) hero.move(direction);
        }
        if (keyPressed == KeyEvent.VK_LEFT){
            Vector2D direction = Direction.LEFT.asVector();
            if(rm.getCurrentRoom().isTileMoveable(position.plus(direction))) hero.move(direction);
        }
        if (keyPressed == KeyEvent.VK_RIGHT){
            Vector2D direction = Direction.RIGHT.asVector();
            if(rm.getCurrentRoom().isTileMoveable(position.plus(direction))) hero.move(direction);
        }
        if (keyPressed == KeyEvent.VK_M) gui.newImages(rm.getNextRoom().getTiles());
        if (keyPressed == KeyEvent.VK_N) gui.newImages(rm.getPreviousRoom().getTiles());
    }

    public static void main(String[] args){
        Engine engine = new Engine();
        engine.init();
    }

}
