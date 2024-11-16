package pt.upskill.projeto1.game.state;

import pt.upskill.projeto1.rogue.utils.data.EnemyData;

import java.io.Serializable;
import java.util.List;

public class RoomState implements Serializable {
    private int currentRoomIndex;
    private List<EnemyData> enemies;

    public RoomState(int currentRoomIndex, List<EnemyData> enemies) {
        this.currentRoomIndex = currentRoomIndex;
        this.enemies = enemies;
    }

    public int getCurrentRoomIndex() {
        return currentRoomIndex;
    }

    public void setCurrentRoomIndex(int currentRoomIndex) {
        this.currentRoomIndex = currentRoomIndex;
    }

    public List<EnemyData> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<EnemyData> enemies) {
        this.enemies = enemies;
    }
}
