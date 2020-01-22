package model.draw;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Movable extends Sprite {
	private double dx,dy;
	private double spd;

	public Movable(Pane layer, Color c, double x, double y, int w, int h, double speed) {
		super(layer, c, x, y, w, h);
		this.dx = 0; this.dy = 0;
		this.spd = speed;
	}

	public Movable(Pane layer, Color c, double x, double y, int w, int h, double dx, double dy, double speed) {
		super(layer, c, x, y, w, h);
		this.dy = 0; this.dy = dy;
		this.spd = speed;
	}

	public double getSpeed() { return this.spd; }
	public void setSpeed(double s) { this.spd = s; }

	public double directionX() { return this.dx; }
    public double directionY() { return this.dy; }
    public void changeDirection(double x, double y) { this.dx = x; this.dy = y; }

    public void move() {
        this.setX(this.getX() + this.dx * this.spd);
        this.setY(this.getY() + this.dy * this.spd);
    }

	public Position getNewPos() {
		double x = this.getX() + this.dx * this.spd;
		double y = this.getY() + this.dx * this.spd;
		return new Position(x, y);
	}
}
