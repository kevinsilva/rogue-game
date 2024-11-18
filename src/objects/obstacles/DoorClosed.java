package pt.upskill.projeto1.objects.obstacles;

import pt.upskill.projeto1.game.GameManager;
import pt.upskill.projeto1.game.RoomManager;
import pt.upskill.projeto1.objects.environment.Door;
import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.objects.characters.Hero;
import pt.upskill.projeto1.objects.Room;
import pt.upskill.projeto1.rogue.utils.Points;
import pt.upskill.projeto1.rogue.utils.Position;

public class DoorClosed extends Door {
    private boolean isOpen;

    public DoorClosed(Position position, int targetRoomIndex, int targetRoomNumber, String keyId) {
        super(position, targetRoomIndex, targetRoomNumber, keyId);
        this.isOpen = false;
    }

    public void unlock() {
        this.isOpen = true;
    }

    @Override
    public String getName() {
        return "DoorClosed";
    }

    @Override
    public boolean isWalkable() {
        return isOpen;
    }

    @Override
    public void react(GameObject otherObject) {
        RoomManager roomManager = RoomManager.getInstance();

        if(otherObject instanceof Hero) {
            Hero hero = (Hero) otherObject;

            if(hero.hasKey(this.getKeyId())) {
                GameManager.getInstance().updateScore(Points.PASS_LOCKED_ROOM.getPoints());

                this.unlock();
                Room nextRoom = roomManager.getRoomAtIndex(this.getTargetRoomIndex());
                Door targetDoor = nextRoom.getDoor(this.getTargetDoorNumber());

                if (targetDoor != null) hero.setPosition(targetDoor.getPosition());
            }
        }
    }
}
