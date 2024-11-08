package pt.upskill.projeto1.game;

import pt.upskill.projeto1.objects.Floor;
import pt.upskill.projeto1.objects.Hero;
import pt.upskill.projeto1.objects.Room;
import pt.upskill.projeto1.objects.Wall;
import pt.upskill.projeto1.rogue.utils.Config;
import pt.upskill.projeto1.rogue.utils.Position;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomManager {
    private List<Room> rooms = new ArrayList<>();
    private Config config = new Config();
    private Room currentRoom;
    private Hero hero;

    public RoomManager(Hero hero) {
        this.hero = hero;
        addRooms();
        if(!rooms.isEmpty()) currentRoom = rooms.getFirst();
    }

    public void addRooms() {
        String[] roomFiles = config.getROOM_FILES();
        for (String roomFile : roomFiles) {
            rooms.add(fileParser(roomFile));
        }
    }

    public Room fileParser(String roomFile) {
        Room room = new Room();
        int y = 0;
        try {
            Scanner fileScanner = new Scanner(new File(config.getROOM_DIRECTORY() + roomFile));
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] letters = line.split("");

                for (int x = 0; x < letters.length; x++) {
                    String letter = letters[x];
                    Position position = new Position(x, y);
                    //System.out.println(letter);
                    this.addTileToRoom(room, letter, position);
                }
                y++;
            }

            fileScanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return room;
    }

    private void headingParser() {

    }

    private void addTileToRoom(Room room, String letter, Position position) {
        switch (letter) {
            case "W":
                room.addTile(new Wall(position));
                break;
            default:
                room.addTile(new Floor(position));
                break;
        }
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public int getCurrentRoomIndex() {
        return this.rooms.indexOf(this.getCurrentRoom());
    }

    public Room getRoomAtIndex(int index) {
        return this.rooms.get(index);
    }

    public Room getNextRoom() {
        if(this.getCurrentRoomIndex() + 1 > config.getROOM_FILES().length - 1) return this.currentRoom;
        Room nextRoom = this.rooms.get(this.getCurrentRoomIndex() + 1);
        nextRoom.addHero(hero);
        currentRoom = nextRoom;
        return nextRoom;
    }

    public Room getPreviousRoom() {
        if(this.getCurrentRoomIndex() - 1 < 0) return this.currentRoom;
        Room previousRoom = this.rooms.get(this.getCurrentRoomIndex() - 1);
        previousRoom.addHero(hero);
        currentRoom = previousRoom;
        return previousRoom;
    }
}
