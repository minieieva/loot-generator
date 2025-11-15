package edu.grinnell.csc207.lootgenerator;

public class Monstats {
    private String monstClass;
    private String treasureClass;

    public Monstats(String monstClass, String treasureClass) {
        this.monstClass = monstClass;
        this.treasureClass = treasureClass;
    }

    public String getName() {
        return monstClass;
    }

    public String getTreasureClass() {
        return treasureClass;
    }
}