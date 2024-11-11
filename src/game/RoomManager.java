package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.objects.*;
import pt.upskill.projeto1.rogue.utils.Constants;
import pt.upskill.projeto1.rogue.utils.Position;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomManager {
    private final Constants CONSTANTS = new Constants();
    private List<Room> rooms = new ArrayList<>();
    private Room currentRoom;
    private final Hero hero;
    ImageMatrixGUI GUI = ImageMatrixGUI.getInstance();

    public RoomManager(Hero hero) {
        this.hero = hero;
        loadRooms();
        if(!rooms.isEmpty()) currentRoom = rooms.getFirst();
    }

    public void loadRooms() {
        String[] roomFiles = CONSTANTS.getROOM_FILENAMES();
        for (String roomFile : roomFiles) {
            rooms.add(fileParser(roomFile));
        }
    }

    public Room fileParser(String roomFile) {
        Room room = new Room();
        int y = 0;
        try {
            Scanner fileScanner = new Scanner(new File(CONSTANTS.getROOM_DIRECTORY() + roomFile));
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] letters = line.split("");

                if (line.startsWith("#")) {
                    headerParser(line, room);
                    continue;
                };

                for (int x = 0; x < letters.length; x++) {
                    this.addToRoom(room, letters[x], new Position(x, y));
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

        if (splitLine.length == 3) addKeyToRoom(splitLine, room);
        else this.addDoorToRoom(splitLine, room);
    }

    private void addDoorToRoom(String[] splitline, Room room) {
        int doorNumber = Integer.parseInt(splitline[1]);
        String doorType = splitline[2];
        int targetRoomIndex = this.getRoomIndexByFileName(splitline[3]);
        int targetDoorNumber = Integer.parseInt(splitline[4]);
        String keyId = splitline.length > 5 ? splitline[5] : null;


        Door door = createDoor(doorType, targetRoomIndex, targetDoorNumber, keyId);
        room.addDoor(doorNumber, door);
    }

    private Door createDoor(String doorType, int targetRoomIndex, int targetDoorNumber, String keyId) {
        switch(doorType.toLowerCase()) {
            case "e":
                return new DoorWay(null, targetRoomIndex, targetDoorNumber);
            case "d":
                if(keyId == null) return new DoorOpen(null, targetRoomIndex, targetDoorNumber);
                return new DoorClosed(null, targetRoomIndex, targetDoorNumber, keyId);
            default:
                return null;
        }
    }

    private void addKeyToRoom(String[] splitline, Room room) {
        Key key = new Key(null, splitline[2]);
        room.addKey(key);
    }


    private void addToRoom(Room room, String letter, Position position) {
        switch (letter.toLowerCase()) {
            case "s":
                room.addGameObject(new Sword(position));
                break;
            case "h":
                room.addGameObject(new Hammer(position));
                break;
            case "m":
                room.addGameObject(new GoodMeat(position));
                break;
            case "w":
                room.addGameObject(new Wall(position));
                break;
            case "0":
            case "1":
            case "2":
                Door door = room.getDoor(Integer.parseInt(letter));
                if (door != null) {
                    door.setPosition(position);
                    room.addGameObject(door);
                }
                break;
            case "k":
                Key key = room.getKey();
                key.setPosition(position);
                room.addGameObject(key);
                break;
            default:
                room.addGameObject(new Floor(position));
                break;
        }
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
        this.currentRoom.addHero(hero);
        this.updateGUI();
    }

    public int getCurrentRoomIndex() {
        return this.rooms.indexOf(this.getCurrentRoom());
    }

    public int getRoomIndexByFileName(String roomFileName) {
        String[] roomFiles = CONSTANTS.getROOM_FILENAMES();

        for(int i = 0; i < roomFiles.length; i++) {
            if(roomFileName.equals(roomFiles[i])) return i;
        }

        throw new IllegalArgumentException("Room file not found: " + roomFileName);
    }

    public Room getRoomAtIndex(int index) {
        if (index >=0 && index < this.rooms.size()) {
            this.setCurrentRoom(this.rooms.get(index));
        }

        return currentRoom;
    }

    public Room getNextRoom() {
        if(this.getCurrentRoomIndex() + 1 < this.rooms.size()) {
            this.setCurrentRoom(this.rooms.get(this.getCurrentRoomIndex() + 1));
        }

       return this.currentRoom;
    }

    public Room getPreviousRoom() {
        if(this.getCurrentRoomIndex() - 1 >= 0) {
            this.setCurrentRoom(this.rooms.get(this.getCurrentRoomIndex() - 1));
        }

        return this.currentRoom;
    }

    public void updateGUI() {
        this.GUI.newImages(this.currentRoom.getGameObjects());
    }
}
