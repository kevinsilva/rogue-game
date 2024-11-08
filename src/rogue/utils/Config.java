package pt.upskill.projeto1.rogue.utils;

public class Config {
    final private String ROOM_DIRECTORY = "rooms/";
    final private String[] ROOM_FILES = {"room0.txt", "room1.txt", "room2.txt"};
    final private Position INITIAL_POSITION = new Position(4, 0);

    public String getROOM_DIRECTORY() {
        return ROOM_DIRECTORY;
    }

    public String[] getROOM_FILES() {
        return ROOM_FILES;
    }

    public Position getINITIAL_POSITION() {
        return INITIAL_POSITION;
    }
}
