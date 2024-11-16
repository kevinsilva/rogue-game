package pt.upskill.projeto1.rogue.utils;

import pt.upskill.projeto1.objects.items.EmptyInventory;
import pt.upskill.projeto1.objects.items.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    // Saves Constants
    public static final String SAVES_FILEPATH = "saves/save.dat";

    // Room Constants
    public static final String[] ROOM_FILENAMES = {"room0.txt", "room1.txt", "room2.txt"};
    public static final String ROOM_DIRECTORY = "rooms/";
    public static final Position INITIAL_POSITION = new Position(4, 0);

    // Player Constants
    public static final int INITIAL_FIREBALLS = 3;
    public static final int INITIAL_HEALTH = 100;
    public static final int STATUS_BAR_LENGTH = 10;

    // Inventory Constants
    public static final int ITEMS_LENGTH = 3;
    public static final List<Inventory> INITIAL_INVENTORY = createInitialInventory();

    // Combat Constants
    public static final int UNARMED_DAMAGE = 5;
    public static final int SWORD_DAMAGE = 10;
    public static final int HAMMER_DAMAGE = 15;

    // Health Constants
    public static final int HEALTH_LENGTH = 4;
    public static final int HEALTH_SCALE = 8;

    //Enemy Constants
    public static final int ENEMY_ALERT_RANGE = 2;

    private static List<Inventory> createInitialInventory() {
        List<Inventory> inventory = new ArrayList<>();
        for (int i = 0; i < ITEMS_LENGTH; i++) {
            inventory.add(new EmptyInventory(null));
        }
        return inventory;
    }
}
