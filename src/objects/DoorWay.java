package pt.upskill.projeto1.objects;

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
}
