package pt.upskill.projeto1.game;

import pt.upskill.projeto1.objects.enemies.Enemy;

public class EnemyThread extends Thread {
    boolean control = true;
    Enemy enemy;

    public EnemyThread(Enemy enemy) {
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
