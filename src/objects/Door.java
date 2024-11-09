package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public abstract class Door extends Obstacle {
    private int targetRoomIndex;
    private int targetDoorNumber;
    private String keyId;

    public Door(Position position, int targetRoomIndex, int targetDoorNumber, String keyId) {
        super(position);
        this.targetRoomIndex = targetRoomIndex;
        this.targetDoorNumber = targetDoorNumber;
        this.keyId = keyId;
    }

    public int getTargetRoomIndex() {
        return this.targetRoomIndex;
    }

    public int getTargetDoorNumber() {
        return this.targetDoorNumber;
    }

    public String getKeyId() {
        return this.keyId;
    }

    public abstract String getName();
}
