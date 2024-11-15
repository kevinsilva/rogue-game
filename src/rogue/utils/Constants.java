package pt.upskill.projeto1.rogue.utils;

import pt.upskill.projeto1.objects.EmptyInventory;
import pt.upskill.projeto1.objects.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    final private String[] ROOM_FILENAMES = {"room0.txt", "room1.txt", "room2.txt"};
    final private Position INITIAL_POSITION = new Position(4, 0);

    public int getINITIAL_FIREBALLS() {
        int INITIAL_FIREBALLS = 3;
        return INITIAL_FIREBALLS;
    }

    public int getINITIAL_HEALTH() {
        int INITIAL_HEALTH = 100;
        return INITIAL_HEALTH;
    }

    public int getHEALTH_SCALE() {
        int HEALTH_SCALE = 8;
        return HEALTH_SCALE;
    }

    public List<Inventory> getINITIAL_INVENTORY() {
        return new ArrayList<>(){
            {
                add(new EmptyInventory(null));
                add(new EmptyInventory(null));
                add(new EmptyInventory(null));
            }
        };
    }

    public int getUNARMED_DAMAGE() {
        int UNARMED_DAMAGE = 5;
        return UNARMED_DAMAGE;
    }

    public int getSWORD_DAMAGE() {
        int SWORD_DAMAGE = 10;
        return SWORD_DAMAGE;
    }

    public int getHAMMER_DAMAGE() {
        int HAMMER_DAMAGE = 15;
        return HAMMER_DAMAGE;
    }

    public int getITEMS_LENGTH() {
        int ITEMS_LENGTH = 3;
        return ITEMS_LENGTH;
    }

    public int getHEALTH_LENGTH() {
        int HEALTH_LENGTH = 4;
        return HEALTH_LENGTH;
    }

    public int getSTATUS_BAR_LENGTH() {
        int STATUS_BAR_LENGTH = 10;
        return STATUS_BAR_LENGTH;
    }

    public int ENEMY_ALERT_RANGE() {
        int ENEMY_ALERT_RANGE = 3;
        return ENEMY_ALERT_RANGE;
    }

    public String getROOM_DIRECTORY() {
        String ROOM_DIRECTORY = "rooms/";
        return ROOM_DIRECTORY;
    }

    public String[] getROOM_FILENAMES() {
        return ROOM_FILENAMES;
    }

    public Position getINITIAL_POSITION() {
        return INITIAL_POSITION;
    }
}
