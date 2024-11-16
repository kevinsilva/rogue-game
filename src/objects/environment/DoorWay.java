package pt.upskill.projeto1.objects.environment;

import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.objects.hero.Hero;
import pt.upskill.projeto1.objects.Room;
import pt.upskill.projeto1.rogue.utils.Position;

public class DoorWay extends Door {
    public DoorWay(Position position, int targetRoomIndex, int targetDoorNumber) {
        super(position, targetRoomIndex, targetDoorNumber, null);
    }

    @Override
    public String getName() {
        return "DoorWay";
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public void react(GameObject otherObject) {
        RoomManager roomManager = RoomManager.getInstance();

        if(otherObject instanceof Hero) {
            Room nextRoom = roomManager.getRoomAtIndex(this.getTargetRoomIndex());
            Door targetDoor = nextRoom.getDoor(this.getTargetDoorNumber());

            if (targetDoor != null) otherObject.setPosition(targetDoor.getPosition());
        }
    }
}
