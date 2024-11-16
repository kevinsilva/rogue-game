package pt.upskill.projeto1.rogue.utils.data;

import java.io.Serializable;

public class InventoryData implements Serializable {
    private String name;

    public InventoryData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
