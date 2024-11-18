package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.characters.Hero;
import pt.upskill.projeto1.rogue.utils.Constants;
import pt.upskill.projeto1.rogue.utils.Direction;
import pt.upskill.projeto1.rogue.utils.GameplayState;

import java.awt.event.KeyEvent;
import java.util.Map;

public class Engine {
    private final GameManager gameManager = GameManager.getInstance();
    private final RoomManager roomManager = RoomManager.getInstance();
    private final SoundManager soundManager = SoundManager.getInstance();
    private final StatusManager statusManager = StatusManager.getInstance();
    private final ImageMatrixGUI GUI = ImageMatrixGUI.getInstance();
    private final Hero hero = Hero.getInstance();
    private final Leaderboard leaderboard = new Leaderboard();
    Direction previousDirection;

    public void init(){
        GUI.setEngine(this);
        roomManager.updateGUI();
        GUI.go();

        while (true){
            GameplayState currentState = gameManager.getGameplayState();

            if (currentState == GameplayState.GAME_START) handleGameStart();
            if (currentState == GameplayState.GAME_ON) handleGameOn();
            if (currentState == GameplayState.GAME_WON) handleGameWon();
            if (currentState == GameplayState.GAME_OVER) handleGameOver();
            if (currentState == GameplayState.GAME_PAUSED) handleGamePaused();

            GUI.update();
        }
    }

    public void handleGameStart() {
        soundManager.loadSound(Constants.SOUND_ROGUE);
        GUI.showMessage("WELCOME TO ROGUE!", Constants.WELCOME_MESSAGE);
        gameManager.setGameplayState(GameplayState.GAME_ON);
    }

    public void handleGameOn() {
    }

    public void handleGamePaused() {
        GUI.showMessage("GAME PAUSED", Constants.PAUSE_MESSAGE);
        gameManager.togglePause();
    }

    public void handleGameWon() {
        soundManager.stopSound();
        soundManager.loadSound(Constants.SOUND_WON);
        GUI.showMessage("VICTORY!", Constants.WIN_MESSAGE);

        String name = GUI.showInputDialog("BE IMMORTAL", Constants.LEADERBOARD_MESSAGE);
        if (name != null) {
            leaderboard.addScore(name, gameManager.getScore());
            GUI.showMessage("LEADERBOARD", leaderboard.displayScores());
        }

        String answer = GUI.showInputDialog("START AGAIN?", Constants.RESTART_MESSAGE);
        if ("R".equalsIgnoreCase(answer)) {
            gameManager.restartGame();
        } else {
            System.exit(0);
        }
    }

    public void handleGameOver() {
        roomManager.getCurrentRoom().removeGameObject(hero);
        soundManager.stopSound();
        soundManager.loadSound(Constants.SOUND_OVER);
        GUI.showMessage("GAME OVER!", Constants.LOSE_MESSAGE);

        String name = GUI.showInputDialog("BE IMMORTAL", Constants.LEADERBOARD_MESSAGE);
        if (name != null) {
            leaderboard.addScore(name, gameManager.getScore());
            GUI.showMessage("LEADERBOARD", leaderboard.displayScores());
        }

        String answer = GUI.showInputDialog("TRY AGAIN?", Constants.LOAD_MESSAGE);
        if ("L".equalsIgnoreCase(answer)) {
            soundManager.stopSound();
            soundManager.loadSound(Constants.SOUND_ROGUE);
            gameManager.loadGame();
        } else {
            gameManager.restartGame();
        }
    }

    public void notify(int keyPressed){
        Direction direction = null;

        if (keyPressed == KeyEvent.VK_DOWN){
            direction = Direction.DOWN;
            previousDirection = Direction.DOWN;
        }
        if (keyPressed == KeyEvent.VK_UP){
            direction = Direction.UP;
            previousDirection = Direction.UP;
        }
        if (keyPressed == KeyEvent.VK_LEFT){
            direction = Direction.LEFT;
            previousDirection = Direction.LEFT;
        }
        if (keyPressed == KeyEvent.VK_RIGHT){
            direction = Direction.RIGHT;
            previousDirection = Direction.RIGHT;
        }

        if(direction != null) {
            hero.move(direction.asVector());
        };

        if (keyPressed == KeyEvent.VK_SPACE) hero.throwFireball(previousDirection);
        if (keyPressed == KeyEvent.VK_1) hero.removeItem(0);
        if (keyPressed == KeyEvent.VK_2) hero.removeItem(1);
        if (keyPressed == KeyEvent.VK_3) hero.removeItem(2);
        if (keyPressed == KeyEvent.VK_T) hero.setTrap();
        if (keyPressed == KeyEvent.VK_P) GameManager.getInstance().togglePause();
    }
}
