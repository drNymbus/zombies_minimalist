package model.item;

import model.draw.*;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.draw.Sprite;

/*
 * Bullet class
 */
public class Bullet extends Movable {
    private int owner;
    private int dmg;
    private int range;

    public Bullet(Pane layer, Color c, double x, double y, int width, int height, int owner, int dmg, double velocity, int range) {
        super(layer, c, x - width/2, y - height/2, width, height, velocity);
        this.owner = owner;
        this.dmg = dmg;
        this.range = range;
    }

    public Bullet(Bullet b) {
        super(b.getLayer(), b.getColor(), b.getX(), b.getY(), b.getWidth(), b.getHeight(), b.getSpeed());
        this.owner = b.getOwner();
        this.dmg = b.getDamage();
        this.range = b.getRange();
    }

    public int getOwner() { return this.owner; }
    public int getDamage() { return this.dmg; }
    public int getRange() { return this.range; }
}
