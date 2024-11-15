package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.*;
import pt.upskill.projeto1.rogue.utils.Constants;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.*;

public class StatusManager {
    ImageMatrixGUI GUI = ImageMatrixGUI.getInstance();
    Constants CONSTANTS = new Constants();
    private HeroStatus heroStatus;
    List<ImageTile> statusBar = new ArrayList<>();

    public StatusManager(HeroStatus heroStatus) {
        this.heroStatus = heroStatus;
        this.updateStatusBar();
        this.loadInitialMessage();
    }

    public void updateStatusBar() {
        clearStatusBar();
        addBarBackground();
        addFireballs();
        addHealth();
        addInventory();
        GUI.newStatusImages(statusBar);
    }

    public void clearStatusBar() {
        GUI.clearStatus();
        statusBar.clear();
    }

    public void addBarBackground() {
        for (int i = 0; i < CONSTANTS.getSTATUS_BAR_LENGTH(); i++) {
            statusBar.add(new Black(new Position(i, 0)));
        }
        GUI.newStatusImages(statusBar);
    }

    public void addFireballs() {
        for (int i = 0; i < heroStatus.getFireballs(); i++) {
            statusBar.set(i, new Fire(new Position(i, 0), null, null));
        }
    }

    public void addHealth() {
        int health = heroStatus.getHealth();
        int scaledHealth = (int) Math.round((double) health / (CONSTANTS.getINITIAL_HEALTH() / CONSTANTS.getHEALTH_SCALE()));
        int healthStartIndex = CONSTANTS.getITEMS_LENGTH();

        for (int i = 0; i < CONSTANTS.getHEALTH_LENGTH(); i++) {
            int index = healthStartIndex + i;
            if (scaledHealth > 2 * i + 1) {
                statusBar.set(index, new Green(new Position(index, 0)));
            } else if (scaledHealth == 2 * i + 1) {
                statusBar.set(index, new GreenRed(new Position(index, 0)));
            } else {
                statusBar.set(index, new Red(new Position(index, 0)));
            }
        }
    }

    public void addInventory() {
        List<Inventory> inventory = heroStatus.getInventory();
        int inventoryStartIndex = CONSTANTS.getITEMS_LENGTH() + CONSTANTS.getHEALTH_LENGTH();

        for (int i = 0; i < inventory.size(); i++) {
            Inventory item = inventory.get(i);
            int index = inventoryStartIndex + i;

            if(index < CONSTANTS.getSTATUS_BAR_LENGTH() && !(item instanceof EmptyInventory)) {
                item.setPosition(new Position(index, 0));
                statusBar.set(index, item);
            }
        }
    }


    public void addMessage(String message) {
        GUI.setStatus(message);
    }

    public void loadInitialMessage() {
        addMessage("Game Started!");
    }
}