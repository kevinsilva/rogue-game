package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.List;

public class Room {
    private Hero hero;
    private List<ImageTile> tiles;

    public Room(Hero hero) {
        this.hero = hero;
        this.init();
    }

    public void init() {
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                tiles.add(new Floor(new Position(i, j)));
            }
        }
        tiles.add(this.hero);
    }


}
