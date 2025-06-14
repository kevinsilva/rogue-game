package pt.upskill.projeto1.game;

import pt.upskill.projeto1.game.state.GameState;
import pt.upskill.projeto1.game.state.HeroState;
import pt.upskill.projeto1.game.state.RoomState;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.enemies.*;
import pt.upskill.projeto1.objects.characters.Hero;
import pt.upskill.projeto1.objects.items.*;
import pt.upskill.projeto1.rogue.utils.Constants;
import pt.upskill.projeto1.rogue.utils.EnemyType;
import pt.upskill.projeto1.rogue.utils.GameplayState;
import pt.upskill.projeto1.rogue.utils.data.EnemyData;
import pt.upskill.projeto1.rogue.utils.data.InventoryData;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameManager {
    private static final GameManager INSTANCE = new GameManager();
    private int score;
    private Engine engine;
    private ImageMatrixGUI GUI;
    private Hero hero = Hero.getInstance();
    private RoomManager roomManager = RoomManager.getInstance();
    private StatusManager statusManager = StatusManager.getInstance();
    private GameplayState gameplayState = GameplayState.GAME_START;

    private GameManager() {
        this.score = 0;
        this.engine = new Engine();
    }

    public static GameManager getInstance() { return INSTANCE; }

    public void startGame() {
        engine.init();
    }

    public void togglePause() {
        if (gameplayState == GameplayState.GAME_ON) setGameplayState(GameplayState.GAME_PAUSED);
        else if (gameplayState == GameplayState.GAME_PAUSED) setGameplayState(GameplayState.GAME_ON);
    }

    public void restartGame() {
        SoundManager.getInstance().stopSound();
        roomManager.getCurrentRoom().removeGameObject(hero);

        this.score = 0;
        roomManager.clearRooms();
        roomManager.loadRooms();
        roomManager.setCurrentRoom(roomManager.getRoomAtIndex(0));

        Hero hero = roomManager.getCurrentRoom().getHero();

        hero.setPosition(Constants.INITIAL_POSITION);
        hero.getStatus().setHealth(Constants.INITIAL_HEALTH);
        hero.getStatus().setFireballs(Constants.INITIAL_FIREBALLS);
        hero.getStatus().setInventory(Constants.INITIAL_INVENTORY);
        hero.setKeys(new HashMap<>());
        statusManager.updateStatusBar();

        setGameplayState(GameplayState.GAME_START);
        roomManager.updateGUI();
    }

    public GameplayState getGameplayState() {
        return gameplayState;
    }

    public void setGameplayState(GameplayState gameplayState) {
        this.gameplayState = gameplayState;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int points) {
        score += points;
        if (score < 0 ) score = 0;
    }

    public void saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream(Constants.SAVES_FILEPATH);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            List<InventoryData> inventoryData = hero.getStatus().getInventory().stream().map(inventory -> new InventoryData(inventory.getName())).toList();

            HeroState heroState = new HeroState(
                    hero.getHealth(),
                    hero.getStatus().getFireballs(),
                    hero.getPosition(),
                    inventoryData,
                    hero.getKeys()
            );

            List<EnemyData> enemyData = roomManager.getCurrentRoom().getEnemies().stream().map(enemy -> new EnemyData(
                    enemy.getPosition(),
                    enemy.getHealth(),
                    enemy.getType())).toList();

            RoomState roomState = new RoomState(
                    roomManager.getCurrentRoomIndex(),
                    enemyData
            );

            GameState gameState = new GameState(getScore(), heroState, roomState);

            out.writeObject(gameState);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadGame() {
        setGameplayState(GameplayState.GAME_ON);
        try {
            FileInputStream fileIn = new FileInputStream(Constants.SAVES_FILEPATH);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            GameState gameState = (GameState) in.readObject();

            this.score = gameState.getScore();
            HeroState heroState = gameState.getHeroState();
            RoomState roomState = gameState.getRoomState();

            restoreHeroState(heroState);
            restoreRoomState(roomState);

            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e ) {
            throw new RuntimeException(e);
        }
    }

    private void restoreHeroState(HeroState heroState) {
        List<Inventory> inventory = new ArrayList<>();
        hero.setHealth(heroState.getHealth());
        hero.getStatus().setHealth(heroState.getHealth());
        hero.getStatus().setFireballs(heroState.getFireballs());
        hero.setPosition(heroState.getPosition());
        hero.setKeys(heroState.getKeys());

        for (InventoryData inventoryData : heroState.getInventory()) {
            inventory.add(createInventoryFromData(inventoryData));
        }

        hero.getStatus().setInventory(inventory);
        StatusManager.getInstance().updateStatusBar();
    }

    private void restoreRoomState(RoomState roomState) {
        roomManager.getCurrentRoom().clearEnemies();
        roomManager.getRoomAtIndex(roomState.getCurrentRoomIndex());


        for (EnemyData enemyData : roomState.getEnemies()) {
            Enemy enemy = createEnemyFromData(enemyData);
            roomManager.getCurrentRoom().addEnemy(enemy);
            roomManager.getCurrentRoom().addGameObject(enemy);
        }

        roomManager.getCurrentRoom().moveEnemies();
    }

    private Inventory createInventoryFromData(InventoryData inventoryData) {
        switch (inventoryData.getName()) {
            case "Sword":
                return new Sword(null);
            case "Hammer":
                return new Hammer(null);
            case "Trap":
                return new Trap(null);
            default:
                return new EmptyInventory(null);
        }
    }

    private Enemy createEnemyFromData(EnemyData enemyData) {
        switch (enemyData.getType()) {
            case EnemyType.PRISONER:
                Prisoner prisoner = new Prisoner(enemyData.getPosition());
                prisoner.setHealth(enemyData.getHealth());
                return prisoner;
            case EnemyType.SKELETON:
                Skeleton skeleton = new Skeleton(enemyData.getPosition());
                skeleton.setHealth(enemyData.getHealth());
                return skeleton;
            case EnemyType.THIEF:
                Thief thief = new Thief(enemyData.getPosition());
                thief.setHealth(enemyData.getHealth());
                return thief;
            case EnemyType.EVILBAT:
                EvilBat evilBat = new EvilBat(enemyData.getPosition());
                evilBat.setHealth(enemyData.getHealth());
                return evilBat;
            case EnemyType.BOSS:
                Boss boss = new Boss(enemyData.getPosition());
                boss.setHealth(enemyData.getHealth());
                return boss;
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.startGame();
    }
}
