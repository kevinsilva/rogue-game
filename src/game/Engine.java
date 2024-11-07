package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.Floor;
import pt.upskill.projeto1.objects.Hero;
import pt.upskill.projeto1.objects.Room;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Engine {


    public void init(){
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

        /*List<ImageTile> tiles = new ArrayList<>();
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                tiles.add(new Floor(new Position(i, j)));
            }
        }*/
        Hero hero = Hero.getInstance();
        //tiles.add(hero);

        RoomManager rm = new RoomManager(hero);
        Room current = rm.getCurrentRoom();
        current.addHero(hero);

        current.getTiles().forEach(item -> System.out.println(item.getPosition()));
        System.out.println(current.getTileByPosition(1, 0).getName());

        gui.setEngine(this);
        //gui.newImages(tiles);
        gui.newImages(current.getTiles());
        gui.go();

        gui.setStatus("O jogo começou!");

        while (true){
            gui.update();
        }
    }

    public void notify(int keyPressed){
        Hero hero = Hero.getInstance();
        if (keyPressed == KeyEvent.VK_DOWN){
            hero.move(Direction.DOWN.asVector());
            System.out.println("User pressed down key!");
        }
        if (keyPressed == KeyEvent.VK_UP){
            hero.move(Direction.UP.asVector());
            System.out.println("User pressed up key!");
        }
        if (keyPressed == KeyEvent.VK_LEFT){
            hero.move(Direction.LEFT.asVector());
            System.out.println("User pressed left key!");
        }
        if (keyPressed == KeyEvent.VK_RIGHT){
            hero.move(Direction.RIGHT.asVector());
            System.out.println("User pressed right key!");
        }
    }

    public static void main(String[] args){
        Engine engine = new Engine();
        engine.init();
    }

}
