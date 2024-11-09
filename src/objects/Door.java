package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public abstract class Door extends Obstacle {
    private String targetRoomFile;
    private int targetDoorNumber;
    private String keyId;

    public Door(Position position, String targetRoomFile, int targetDoorNumber, String keyId) {
        super(position);
        this.targetRoomFile = targetRoomFile;
        this.targetDoorNumber = targetDoorNumber;
        this.keyId = keyId;
    }

    public String getTargetRoomFile() {
        return this.targetRoomFile;
    }

    public int getTargetDoorNumber() {
        return this.targetDoorNumber;
    }

    public String getKeyId() {
        return this.keyId;
    }

    public abstract String getName();
}
