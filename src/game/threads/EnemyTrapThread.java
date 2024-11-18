package pt.upskill.projeto1.game.threads;
import pt.upskill.projeto1.objects.enemies.Enemy;

public class EnemyTrapThread extends Thread {
    private boolean control = true;
    private final Enemy enemy;

    public EnemyTrapThread(Enemy enemy) {
        this.enemy = enemy;
    }

    public void stopThread() {
        this.control = false;
    }

    @Override
    public void run() {
        while (control) {
            try {
                Thread.sleep(5000);
                enemy.setTrapped(false);
                stopThread();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}