package model.drawable;

import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Movable extends Sprite {
	private int dx,dy;
	private int spd;

	public Movable(Pane layer, Color c, int x, int y, int w, int h, int speed) {
		super(layer, c, x, y, w, h);
		this.dx = 0; this.dy = 0;
		this.spd = speed;
	}

	public int getSpeed() { return this.spd; }
	public void setSpeed(int s) { this.spd = s; }

	public int directionX() { return this.dx; }
    public int directionY() { return this.dy; }
    public void changeDirection(int x, int y) { this.dx = x; this.dy = y; }

    public void move() {
        this.setX(this.getX() + this.dx * this.spd);
        this.setY(this.getY() + this.dy * this.spd);
    }

	public void move(int x, int y) {
		this.setX(this.getX() + x * this.spd);
		this.setX(this.getY() + y * this.spd);
	}
}
