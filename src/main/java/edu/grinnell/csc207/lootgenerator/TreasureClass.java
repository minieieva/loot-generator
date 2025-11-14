package edu.grinnell.csc207.lootgenerator;

public class TreasureClass {
    private String treasureClassName;
    private String[] items;

    public TreasureClass(String treasureClassName, String[] items) {
        this.treasureClassName = treasureClassName;
        this.items = items;
    }

    public String getTreasureClassName() {
        return treasureClassName;
    }

    public String[] getItems() {
        return items;
    }
}
