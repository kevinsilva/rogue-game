package pt.upskill.projeto1.game.threads;

import pt.upskill.projeto1.objects.enemies.Enemy;

public class EnemyMoveThread extends Thread {
    boolean control = true;
    Enemy enemy;

    public EnemyMoveThread(Enemy enemy) {
        this.enemy = enemy;
    }

    public void stopThread() {
        this.control = false;
    }

    @Override
    public void run() {
        while (control) {
            try {
                sleep(1000);
                enemy.move();
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }

        }
    }
}
