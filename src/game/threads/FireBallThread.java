package pt.upskill.projeto1.game.threads;

import pt.upskill.projeto1.gui.FireTile;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.Position;

public class FireBallThread extends Thread {

    private Direction direction;
    private FireTile fireTile;

    public FireBallThread(Direction direction, FireTile fireTile) {
        this.direction = direction;
        this.fireTile = fireTile;
    }

    @Override
    public void run() {
        boolean control = true;
        while (control) {
            Position nextPosition = fireTile.getPosition().plus(direction.asVector());
            fireTile.setPosition(nextPosition);
            try {
                if (fireTile.validateImpact()) {
                    // FireBall continue
                    sleep(300);
                }else{
                    // FireBall should explode and stop is job
                    sleep(500);
                    control = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Remove FireBall of {ImageMatrixGUI}
        ImageMatrixGUI.getInstance().removeImage(fireTile);
    }
}
