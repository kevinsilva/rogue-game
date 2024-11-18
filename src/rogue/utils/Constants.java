package pt.upskill.projeto1.rogue.utils;

import pt.upskill.projeto1.objects.items.EmptyInventory;
import pt.upskill.projeto1.objects.items.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    // Path Constants
    public static final String SAVES_FILEPATH = "saves/save.dat";
    public static final String LEADERBOARD_FILEPATH = "saves/leaderboard.txt";
    public static final String SOUND_FILEPATH = "sound/";
    public static final String SOUND_WON = "game_won.wav";
    public static final String SOUND_OVER = "game_over.wav";
    public static final String SOUND_BATTLE = "battle.wav";
    public static final String SOUND_ROGUE = "rogue.wav";


    // Room Constants
    public static final String[] ROOM_FILENAMES = {"room0.txt", "room1.txt", "room2.txt", "room3.txt"};
    public static final String ROOM_DIRECTORY = "rooms/";
    public static final Position INITIAL_POSITION = new Position(4, 8);

    // Player Constants
    public static final int INITIAL_FIREBALLS = 3;
    public static final int INITIAL_HEALTH = 100;
    public static final int STATUS_BAR_LENGTH = 10;

    // Inventory Constants
    public static final int ITEMS_LENGTH = 3;
    public static final List<Inventory> INITIAL_INVENTORY = createInitialInventory();

    // Health Constants
    public static final int HEALTH_LENGTH = 4;
    public static final int HEALTH_SCALE = 8;

    //Enemy Constants
    public static final int ENEMY_ALERT_RANGE = 2;

    // Messages Constants
    public static final  String WELCOME_MESSAGE = "The Kingdom of Isctoria lies in great DANGER! \n" +
            "The wicked Devil Lord has kidnapped Princess Ariel within the Shadow Castle.\n" +
            "Only you possess the courage and strength to face vile enemies and restore HOPE to the land.\n\n" +
            "Controls:\n" +
            "Move: Arrow Keys (↑ ↓ ← →)\n" +
            "Throw Fireball: SPACE\n" +
            "Set Trap: T\n" +
            "Remove Item from Inventory: 1, 2, 3\n\n" +
            "Important Tips:\n" +
            "- Grass tiles act as checkpoints. Walking over them will save your game progress.\n" +
            "- Combat is simple: To attack enemies, just touch them.";

    public static final  String PAUSE_MESSAGE = "Press ENTER to resume game.";
    public static final  String WIN_MESSAGE = "You have defeated the wicked Devil Lord and rescued Princess Ariel!";
    public static final  String LOSE_MESSAGE = "The wicked Devil Lord's darkness remains. Brave hero, your journey ends here.";
    public static final  String LOAD_MESSAGE = "Believe in the light! Start new game (N) or load game (L)?";
    public static final  String RESTART_MESSAGE = "Restart adventure (R) or quit (Q)?";
    public static final  String LEADERBOARD_MESSAGE = "Enter your name to be immortalized on the leaderboard!";

    private static List<Inventory> createInitialInventory() {
        List<Inventory> inventory = new ArrayList<>();
        for (int i = 0; i < ITEMS_LENGTH; i++) {
            inventory.add(new EmptyInventory(null));
        }
        return inventory;
    }
}
