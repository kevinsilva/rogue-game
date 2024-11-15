package pt.upskill.projeto1.game;

import pt.upskill.projeto1.objects.Enemy;

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
                throw new RuntimeException(e);
            }

        }
    }
}
