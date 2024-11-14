package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.Hero;

public class GameManager {
    private int score;
    private Engine engine;
    private ImageMatrixGUI GUI;
    private Hero hero;
    private RoomManager roomManager;
    private StatusManager statusManager;

    public GameManager() {
        this.score = 0;
        this.hero = Hero.getInstance();
        this.GUI = ImageMatrixGUI.getInstance();
        this.roomManager = new RoomManager(hero);
        this.statusManager = new StatusManager(hero.getStatus());
        this.engine = new Engine(roomManager, statusManager);
    }

    public void startGame() {
        engine.init();
    }

    public int getScore() {
        return score;
    }

    public void updateScore() {
    }

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.startGame();
    }
}
