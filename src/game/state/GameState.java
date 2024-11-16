package pt.upskill.projeto1.game.state;

import java.io.Serializable;

public class GameState implements Serializable {
    private int score;
    private HeroState heroState;
    private RoomState roomState;

    public GameState(int score, HeroState heroState, RoomState roomState) {
        this.score = score;
        this.heroState = heroState;
        this.roomState = roomState;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public HeroState getHeroState() {
        return heroState;
    }

    public void setHeroState(HeroState heroState) {
        this.heroState = heroState;
    }

    public RoomState getRoomState() {
        return roomState;
    }

    public void setRoomState(RoomState roomState) {
        this.roomState = roomState;
    }
}
