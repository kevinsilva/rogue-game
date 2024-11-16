package pt.upskill.projeto1.objects.items;

import pt.upskill.projeto1.rogue.utils.Position;

public class Key extends Item {
    private String keyId;

    public Key(Position position, String keyId) {
        super(position);
        this.keyId = keyId;
    }

    public String getKeyId() {
        return this.keyId;
    }

    @Override
    public String getName() {
        return "Key";
    }
}
