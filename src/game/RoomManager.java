package pt.upskill.projeto1.game;

import pt.upskill.projeto1.objects.*;
import pt.upskill.projeto1.objects.characters.Princess;
import pt.upskill.projeto1.objects.enemies.*;
import pt.upskill.projeto1.objects.environment.*;
import pt.upskill.projeto1.objects.characters.Hero;
import pt.upskill.projeto1.objects.items.*;
import pt.upskill.projeto1.objects.obstacles.DoorClosed;
import pt.upskill.projeto1.objects.obstacles.Wall;
import pt.upskill.projeto1.rogue.utils.Constants;
import pt.upskill.projeto1.rogue.utils.GameplayState;
import pt.upskill.projeto1.rogue.utils.Position;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomManager {
    private Hero hero = Hero.getInstance();
    private static final RoomManager INSTANCE = new RoomManager();
    private List<Room> rooms = new ArrayList<>();
    private Room currentRoom;
    private StatusManager statusManager;

    private RoomManager() {
        loadRooms();
        if(!rooms.isEmpty()) {
            currentRoom = rooms.getFirst();
            currentRoom.addHero(hero);
            currentRoom.addEnemiesToGameObjects();
        }
    }

    public static RoomManager getInstance() { return INSTANCE; }

    public void loadRooms() {
        String[] roomFiles = Constants.ROOM_FILENAMES;
        for (String roomFile : roomFiles) {
            rooms.add(fileParser(roomFile));
        }
    }

    public Room fileParser(String roomFile) {
        Room room = new Room();
        int y = 0;
        try {
            Scanner fileScanner = new Scanner(new File(Constants.ROOM_DIRECTORY + roomFile));
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
        switch (letter) {
            case "s":
                room.addGameObject(new Sword(position));
                break;
            case "h":
                room.addGameObject(new Hammer(position));
                break;
            case "m":
                room.addGameObject(new GoodMeat(position));
                break;
            case "W":
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
            case "u":
                room.addGameObject(new StairsUp(position));
                break;
            case "d":
                room.addGameObject(new StairsDown(position));
                break;
            case "G":
                room.addGameObject(new Grass(position));
                break;
            case "k":
                Key key = room.getKey();
                key.setPosition(position);
                room.addGameObject(key);
                break;
            case "t":
                room.addGameObject(new Trap(position));
                break;
            case "B":
                Boss boss = new Boss(position);
                room.addEnemy(boss);
                break;
            case "S":
                Skeleton skeleton = new Skeleton(position);
                room.addEnemy(skeleton);
                break;
            case "P":
                Prisoner prisoner = new Prisoner(position);
                room.addEnemy(prisoner);
                break;
            case "T":
                Thief thief = new Thief(position);
                room.addEnemy(thief);
                break;
            case "E":
                EvilBat evilBat = new EvilBat(position);
                room.addEnemy(evilBat);
                break;
            case "p":
                room.addGameObject(new Princess(position));
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
        this.currentRoom.stopEnemies();

        this.currentRoom = currentRoom;
        this.currentRoom.addHero(hero);
        this.currentRoom.addEnemiesToGameObjects();

        if (isLastRoom()) {
            SoundManager soundManager = SoundManager.getInstance();

            soundManager.stopSound();
            soundManager.loadSound(Constants.SOUND_BATTLE);
        }

        this.updateGUI();
    }

    public void clearRooms() {
        rooms.clear();
    }

    public int getCurrentRoomIndex() {
        return this.rooms.indexOf(this.getCurrentRoom());
    }

    public int getRoomIndexByFileName(String roomFileName) {
        String[] roomFiles = Constants.ROOM_FILENAMES;

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

    public boolean isLastRoom() {
        return getCurrentRoomIndex() == rooms.size() - 1;
    }

    public void updateGUI() {
        this.currentRoom.updateGUI();
    }
}
