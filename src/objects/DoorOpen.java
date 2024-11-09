package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.rogue.utils.Position;

public class DoorOpen extends Door {
    public DoorOpen(Position position, int targetRoomIndex, int targetDoorNumber) {
        super(position, targetRoomIndex, targetDoorNumber, null);
    }

    @Override
    public String getName() {
        return "DoorOpen";
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public void react(GameObject otherObject, RoomManager roomManager) {
        if(otherObject instanceof Hero) {
            Room nextRoom = roomManager.getRoomAtIndex(this.getTargetRoomIndex());
            Door targetDoor = nextRoom.getDoor(this.getTargetDoorNumber());

            if (targetDoor != null) otherObject.setPosition(targetDoor.getPosition());
        }
    }
}
