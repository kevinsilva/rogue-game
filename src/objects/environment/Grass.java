package pt.upskill.projeto1.objects.environment;

import pt.upskill.projeto1.game.GameManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.objects.characters.Hero;
import pt.upskill.projeto1.rogue.utils.Position;

public class Grass extends GameObject {
    public Grass(Position position) {
        super(position);
    }

    @Override
    public void react(GameObject otherObject) {
        StatusManager statusManager = StatusManager.getInstance();
        if (otherObject instanceof Hero) {
            statusManager.addMessage("Game Saved!");
            GameManager.getInstance().saveGame();
        }
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public String getName() {
        return "Grass";
    }
}
