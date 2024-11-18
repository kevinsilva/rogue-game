package pt.upskill.projeto1.game;

import pt.upskill.projeto1.game.state.GameState;
import pt.upskill.projeto1.rogue.utils.Constants;

import java.io.*;
import java.util.*;

public class Leaderboard implements Serializable {
    private Map<String, Integer> scores = new HashMap<>();

    public void loadScores () {
        try {
        FileInputStream fileIn = new FileInputStream(Constants.LEADERBOARD_FILEPATH);
        ObjectInputStream in = new ObjectInputStream(fileIn);

        scores = (Map<String, Integer>) in.readObject();

        } catch (RuntimeException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveScores () {
        try {
        FileOutputStream fileOut = new FileOutputStream(Constants.LEADERBOARD_FILEPATH);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);

        out.writeObject(scores);
        out.close();
        fileOut.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addScore(String playerName, int score) {
        loadScores();
        if (scores.containsKey(playerName)) {
            if (score > scores.get(playerName)) scores.put(playerName, score);
        } else {
            scores.put(playerName, score);
        }

        saveScores();
    }

    public Map<String, Integer> getScores() {
        return scores;
    }

    private List<Map.Entry<String, Integer>> sortScores() {
        List<Map.Entry<String, Integer>> scoreList = new ArrayList<>(scores.entrySet());

        Collections.sort(scoreList, (score1, score2) -> score2.getValue().compareTo(score1.getValue()));
        return scoreList;
    }

    public String displayScores() {
        String leaderboard = "LEADERBOARD\n" + "--------------------------\n\n";

        for (Map.Entry<String, Integer> entry : sortScores()) {
            leaderboard += entry.getKey() + " - " + entry.getValue() + "\n";
        }

        return leaderboard;
    }
}
