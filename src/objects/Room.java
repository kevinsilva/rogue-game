package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room {
    private List<ImageTile> tiles = new ArrayList<>();

    public Room() {
        this.init();
    }

    public void init() {
        for(int y=0; y<10; y++){
            for(int x=0; x<10; x++){
                this.addTile(new Floor(new Position(x, y)));
            }
        }
    }

    // Should be generic type?
    public void addTile(ImageTile tile) {
        this.tiles.add(tile);
    }

    public void addHero(Hero hero) {
        tiles.add(hero);
    }

    public List<ImageTile> getTiles() {
        return this.tiles;
    }

    public ImageTile getTileByPosition(int x, int y) {
        if(x < 0 || x > 9 && y < 0 || y > 9) return null;
        ImageTile tileByPosition = null;

        for(ImageTile tile : this.getTiles()) {
            int tileX = tile.getPosition().getX();
            int tileY = tile.getPosition().getY();
            if(tileX == x && tileY == y) {
                tileByPosition = tile;
            }
        }
        return tileByPosition;
    }

    public boolean isTileMoveable(Position newPosition) {
        boolean isMoveable = false;
        ImageTile tile = getTileByPosition(newPosition.getX(), newPosition.getY());
        if(tile == null) return false;

        return !(tile instanceof Wall);
        //return !Objects.equals(tile.getName(), "Wall");
    }
}
