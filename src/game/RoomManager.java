package pt.upskill.projeto1.game;

import pt.upskill.projeto1.objects.*;
import pt.upskill.projeto1.rogue.utils.Constants;
import pt.upskill.projeto1.rogue.utils.Position;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomManager {
    private Constants constants = new Constants();
    private List<Room> rooms = new ArrayList<>();
    private Room currentRoom;
    private Hero hero;

    public RoomManager(Hero hero) {
        this.hero = hero;
        addRooms();
        if(!rooms.isEmpty()) currentRoom = rooms.getFirst();
    }

    public void addRooms() {
        String[] roomFiles = constants.getROOM_FILES();
        for (String roomFile : roomFiles) {
            rooms.add(fileParser(roomFile));
        }
    }

    public Room fileParser(String roomFile) {
        Room room = new Room();
        int y = 0;
        try {
            Scanner fileScanner = new Scanner(new File(constants.getROOM_DIRECTORY() + roomFile));
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] letters = line.split("");

                if (line.startsWith("#")) {
                    headerParser(line, room);
                    continue;
                };

                for (int x = 0; x < letters.length; x++) {
                    this.addTileToRoom(room, letters[x], new Position(x, y));
                }
                y++;
            }

            fileScanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return room;
    }

    private void headerParser(String line, Room room) {
        String[] splitLine = line.split(" ");
        if (splitLine.length < 3) return;
        if (splitLine.length == 3) keyInfoParser(splitLine, room);
        else this.doorInfoParser(splitLine, room);
    }

    private void doorInfoParser(String[] splitline, Room room) {
        Door door;
        int doorNumber = Integer.parseInt(splitline[1]);
        String doorType = splitline[2];
        String targetRoomFile = splitline[3];
        int targetDoorNumber = Integer.parseInt(splitline[4]);
        String keyId = splitline.length > 5 ? splitline[5] : null;

        if(doorType.equalsIgnoreCase("e")) door = new DoorWay(null, targetRoomFile, targetDoorNumber);

        door = (keyId == null) ?
                new DoorOpen(null, targetRoomFile, targetDoorNumber) :
                new DoorClosed(null, targetRoomFile, targetDoorNumber, keyId);

        room.addDoor(doorNumber, door);
    }

    private void keyInfoParser(String[] splitline, Room room) {
        Key key = new Key(null, splitline[2]);
        room.addKey(key);
    }


    private void addTileToRoom(Room room, String letter, Position position) {
        switch (letter.toLowerCase()) {
            case "w":
                room.addTile(new Wall(position));
                break;
            case "0":
            case "1":
            case "2":
                Door door = room.getDoor(Integer.parseInt(letter));
                if (door != null) {
                    door.setPosition(position);
                    room.addTile(door);
                }
                break;
            case "k":
                Key key = room.getKey();
                key.setPosition(position);
                room.addTile(key);
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
        if(this.getCurrentRoomIndex() + 1 < this.rooms.size()) {
            this.currentRoom =  this.rooms.get(this.getCurrentRoomIndex() + 1);
            this.currentRoom.addHero(hero);
        }

       return this.currentRoom;
    }

    public Room getPreviousRoom() {
        if(this.getCurrentRoomIndex() - 1 >= 0) {
            this.currentRoom = this.rooms.get(this.getCurrentRoomIndex() - 1);
            this.currentRoom.addHero(hero);
        }

        return this.currentRoom;
    }
}
