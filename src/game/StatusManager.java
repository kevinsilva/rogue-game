package pt.upskill.projeto1.game;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.fire.Fire;
import pt.upskill.projeto1.objects.characters.Hero;
import pt.upskill.projeto1.objects.characters.HeroStatus;
import pt.upskill.projeto1.objects.items.EmptyInventory;
import pt.upskill.projeto1.objects.items.Inventory;
import pt.upskill.projeto1.objects.ui.Black;
import pt.upskill.projeto1.objects.ui.Green;
import pt.upskill.projeto1.objects.ui.GreenRed;
import pt.upskill.projeto1.objects.ui.Red;
import pt.upskill.projeto1.rogue.utils.Constants;
import pt.upskill.projeto1.rogue.utils.Position;

import java.util.*;

public class StatusManager {
    private static final StatusManager INSTANCE = new StatusManager();
    ImageMatrixGUI GUI = ImageMatrixGUI.getInstance();
    private HeroStatus heroStatus = Hero.getInstance().getStatus();
    List<ImageTile> statusBar = new ArrayList<>();

    private StatusManager() {
        this.updateStatusBar();
        this.loadInitialMessage();
    }

    public static StatusManager getInstance() { return INSTANCE; }

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
        for (int i = 0; i < Constants.STATUS_BAR_LENGTH; i++) {
            statusBar.add(new Black(new Position(i, 0)));
        }
        GUI.newStatusImages(statusBar);
    }

    public void addFireballs() {
        for (int i = 0; i < heroStatus.getFireballs(); i++) {
            statusBar.set(i, new Fire(new Position(i, 0), null));
        }
    }

    public void addHealth() {
        int health = heroStatus.getHealth();
        int convertedTotalHealth = Constants.INITIAL_HEALTH / Constants.HEALTH_SCALE;
        int scaledHealth = (int) Math.round((double) health / convertedTotalHealth);
        int healthStartIndex = Constants.ITEMS_LENGTH;

        for (int i = 0; i < Constants.HEALTH_LENGTH; i++) {
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
        int inventoryStartIndex = Constants.ITEMS_LENGTH + Constants.HEALTH_LENGTH;

        for (int i = 0; i < inventory.size(); i++) {
            Inventory item = inventory.get(i);
            int index = inventoryStartIndex + i;

            if(index < Constants.STATUS_BAR_LENGTH && !(item instanceof EmptyInventory)) {
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