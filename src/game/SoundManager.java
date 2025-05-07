package pt.upskill.projeto1.game;

import pt.upskill.projeto1.rogue.utils.Constants;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {
    private static final SoundManager INSTANCE = new SoundManager();
    private Clip clip;

    public static SoundManager getInstance() { return INSTANCE; }

    public void loadSound(String fileName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Constants.SOUND_FILEPATH + fileName));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            playSound();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("SoundManager: Could not load sound file: " + fileName);
        }
    }

    public void playSound() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
