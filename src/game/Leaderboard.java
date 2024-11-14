package pt.upskill.projeto1.game;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {
    private List<Integer> scores = new ArrayList<>();

    public void loadScores (String filePath) {}
    public void saveScores (String filePath) {}
    public void addScore(int score) {
        scores.add(score);
        //sort
    }

    public List<Integer> getScores() {
        return scores;
    }
}
