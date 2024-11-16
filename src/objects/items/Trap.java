package pt.upskill.projeto1.objects.items;

import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.rogue.utils.Position;

public class Trap extends Inventory {
    private boolean isArmed;
    public Trap(Position position){
        super(position);
        isArmed = false;
    }

    public void setIsArmed() {
        System.out.println("armed");
        isArmed = true;
    }

    public boolean getIsArmed() {
        return isArmed;
    }


    @Override
    public String getName() {
        return "Trap";
    }

    @Override
    public void react(GameObject otherObject) {}
}
