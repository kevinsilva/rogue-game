package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.game.StatusManager;
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
    public void react(GameObject otherObject, RoomManager roomManager, StatusManager statusManager) {
        if(otherObject instanceof Hero) {
            Room nextRoom = roomManager.getRoomAtIndex(this.getTargetRoomIndex());
            Door targetDoor = nextRoom.getDoor(this.getTargetDoorNumber());

            if (targetDoor != null) otherObject.setPosition(targetDoor.getPosition());
        }
    }
}
