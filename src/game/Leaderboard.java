package pt.upskill.projeto1.game;

import pt.upskill.projeto1.game.state.GameState;
import pt.upskill.projeto1.rogue.utils.Constants;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

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
        scores.put(playerName, score);
        saveScores();

        //sort
    }
}
