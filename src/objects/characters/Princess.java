package pt.upskill.projeto1.objects.characters;

import pt.upskill.projeto1.game.GameManager;
import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.objects.enemies.Boss;
import pt.upskill.projeto1.objects.enemies.Enemy;
import pt.upskill.projeto1.rogue.utils.GameplayState;
import pt.upskill.projeto1.rogue.utils.Points;
import pt.upskill.projeto1.rogue.utils.Position;

public class Princess extends GameObject {
    public Princess(Position position) {
        super(position);
    }

    private boolean isBossDead() {
        RoomManager roomManager = RoomManager.getInstance();
        for (Enemy enemy : roomManager.getCurrentRoom().getEnemies()) {
            if (enemy instanceof Boss && enemy.getHealth() > 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void react(GameObject otherObject) {
        if (otherObject instanceof Hero && isBossDead()) {
            GameManager gameManager = GameManager.getInstance();
            StatusManager.getInstance().addMessage("The Princess is safe now!");

            gameManager.updateScore(Points.RESCUE_PRINCESS.getPoints());
            gameManager.setGameplayState(GameplayState.GAME_WON);

        }
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public String getName() {
        return "Princess";
    }
}
