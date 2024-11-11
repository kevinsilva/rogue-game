package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.*;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.*;

public class StatusManager {
    ImageMatrixGUI GUI = ImageMatrixGUI.getInstance();
    private final int ITEMS_LENGTH = 3;
    private final int HEALTH_LENGTH = 4;
    private final int STATUS_BAR_LENGTH = 10;

    List<ImageTile> statusBar = new ArrayList<>();

    public StatusManager() {
        this.loadBar();
        this.loadInitialMessage();
    }

    public void loadBar() {
        addBarBackground();
        addFireballs();
        addHealth();
        updateStatusBar();
    }

    public void addBarBackground() {
        for (int i = 0; i < STATUS_BAR_LENGTH; i++) {
            statusBar.add(new Black(new Position(i, 0)));
        }
        updateStatusBar();
    }

    public void addFireballs() {
        for (int i = 0; i < ITEMS_LENGTH; i++) {
            statusBar.set(i, new Fire(new Position(i, 0)));
        }
    }

    public void removeFireball() {
        for (int i = ITEMS_LENGTH - 1; i >= 0; i--) {
            ImageTile fireTile = statusBar.get(i);

            if (fireTile instanceof Fire) {
                statusBar.set(i, new Black(new Position(i, 0)));
                break;
            }
        }
        updateStatusBar();
    }

    public void addHealth() {
        for (int i = ITEMS_LENGTH; i < ITEMS_LENGTH + HEALTH_LENGTH; i++) {
            statusBar.set(i, new Green(new Position(i, 0)));
        }
    }

    public void removeHealth() {
        for (int i = ITEMS_LENGTH + HEALTH_LENGTH - 1; i >= ITEMS_LENGTH; i--) {
            ImageTile healthTile = statusBar.get(i);

            if (healthTile instanceof Green) {
                statusBar.set(i, new Red(new Position(i, 0)));
                break;
            }
        }

        updateStatusBar();
    }

    public void addItem(Item item) {
        for (int i = ITEMS_LENGTH + HEALTH_LENGTH; i < STATUS_BAR_LENGTH; i++) {
            item.setPosition(new Position(i, 0));
            ImageTile itemTile = statusBar.get(i);

            if (itemTile instanceof Black) {
                statusBar.set(i, item);
                break;
            }
        }
        updateStatusBar();
    }

    public void removeItem(Item item) {
        for (int i = ITEMS_LENGTH + HEALTH_LENGTH; i < STATUS_BAR_LENGTH; i++) {
            ImageTile itemTile = statusBar.get(i);

            if (Objects.equals(itemTile.getName(), item.getName())) {
                statusBar.set(i, new Black(new Position(i, 0)));
                break;
            }
        }
        updateStatusBar();
    }

    public void updateStatusBar() {
        GUI.newStatusImages(statusBar);
    }

    public void addMessage(String message) {
        GUI.setStatus(message);
    }

    public void loadInitialMessage() {
        addMessage("Game Started!");
    }
}