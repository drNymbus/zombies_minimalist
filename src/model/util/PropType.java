package model.util;

import game.*;

import javafx.scene.paint.Color;

/*
 * Enum to init Prop class
 */
public enum PropType {
    // PropType (color, width, height)
    NONE (Settings.COLOR_PROP, Settings.SIZE_HUMAN, Settings.SIZE_HUMAN);

    private Color color;
    private int width, height;

    PropType(Color c, int w, int h) {
        this.color = c;
        this.width = w;
        this.height = h;
    }

    public Color getColor() { return this.color; }
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }

}
