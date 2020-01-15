package model.item;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Bullet extends Sprite {
    private int owner;
    private int dmg;
    private int velocity;
    private int range;

    public Bullet(Pane layer, Color c, int x, int y, int width, int height, int owner, int dmg, int velocity, int range) {
        super(layer, c, x - width/2, y - height/2, width, height);
        this.owner = owner;
        this.dmg = dmg;
        this.velocity = velocity;
        this.range = range;
    }

    public Bullet(Bullet b) {
        super(b.getLayer(), b.getColor(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
        this.owner = b.getOwner();
        this.dmg = b.getDamage();
        this.range = b.getRange();
    }

    public int getOwner() { return this.owner; }
    public int getDamage() { return this.dmg; }
    public int getRange() { return this.range; }
    public int getVelocity() { return this.velocity; }
}
