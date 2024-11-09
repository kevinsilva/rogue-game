package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public class DoorWay extends Door {
    public DoorWay(Position position, String targetRoomFile, int targetDoorNumber) {
        super(position, targetRoomFile, targetDoorNumber, null);
    }

    @Override
    public String getName() {
        return "DoorWay";
    }
}
