package pt.upskill.projeto1.rogue.utils;

public class Constants {
    final private String[] ROOM_FILENAMES = {"room0.txt", "room1.txt", "room2.txt"};
    final private Position INITIAL_POSITION = new Position(4, 0);

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

    public int getSTATUS_BAR_WIDTH() {
        int STATUS_BAR_WIDTH = 10;
        return STATUS_BAR_WIDTH;
    }
}
