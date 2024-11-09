package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public class DoorClosed extends Door {
    private boolean isOpen;

    public DoorClosed(Position position, String targetRoomFile, int targetRoomNumber, String keyId) {
        super(position, targetRoomFile, targetRoomNumber, keyId);
        this.isOpen = false;
    }

    public void unlock() {
        this.isOpen = true;
    }

    public void lock() {
        this.isOpen = false;
    }

    @Override
    public String getName() {
        return "DoorClosed";
    }
}
