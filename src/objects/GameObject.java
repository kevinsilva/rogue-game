package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.Objects;

public abstract class GameObject implements ImageTile {
    protected Position position;

    public GameObject(Position position) {
        this.position = position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GameObject other = (GameObject) obj;
        return Objects.equals(position, other.position) &&
                Objects.equals(getName(), other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, getName());
    }

    public abstract void react(GameObject otherObject);
    public abstract boolean isWalkable();
}
