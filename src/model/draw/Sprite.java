package model.draw;

import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Sprite {
	private Pane layer;
	private Position pos;
	private int width, height;
	private Rectangle base;
	private double angle;
	private Color color;

	public Sprite(Pane layer, Color c, double x, double y, int w, int h) {
		this.pos = new Position(x, y);
		this.width = w; this.height = h;

		this.base = new Rectangle(x - w/2, y - h/2, w, h);
		this.base.setFill(c);
		this.angle = 0;
		this.color = c;

		this.layer = layer;
		this.addToLayer();
	}

	public void setX(double x) { this.pos.setX(x); this.base.setX( x - this.width/2 ); }
	public void setY(double y) { this.pos.setY(y); this.base.setY( y - this.height/2 ); }
	public Position getPosition() { return this.pos; }
	public double getX() { return this.pos.getX(); }
	public double getY() { return this.pos.getY(); }

	public Color getColor() { return this.color; }
	public void setColor(Color c) { this.color = c; this.base.setFill(c); }

	public Pane getLayer() { return this.layer; }
	public Rectangle getShape() { return this.base; }
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }

	public void addToLayer() { this.layer.getChildren().add(this.base); }
	public void removeFromLayer() { this.layer.getChildren().remove(this.base); }

	public boolean isIn(Sprite s) {
		if (this.base.getX() > s.getX() + s.getWidth()/2 || this.base.getX() + this.width + 1 < s.getX() - s.getWidth()/2)
			return false;
		if (this.base.getY() > s.getY() + s.getHeight()/2 || this.base.getY() + this.height + 1 < s.getY() - s.getHeight()/2)
			return false;

		// if (s.getX() - s.getWidth()/2 > this.base.getX() + this.width || s.getX() + s.getWidth()/2 < this.base.getX())
		// 	return false;
		// if (s.getY() - s.getHeight()/2 > this.base.getY() + this.height || s.getY() + s.getHeight()/2 < this.base.getY())
		// 	return false;

		return true;
	}

	/*
	* @brief : applies a rotation from the base center
	* @param angle : [0-360) in degrees
	*/
	public void setRotate(double angle) {
		this.angle = angle; this.base.setRotate(angle);
	}

	/*
	* @brief : applies a rotation from a point at coord (x,y)
	* @param angle : [0-360) in degrees
	* @param x : the x coord of the pivot
	* @param y : the x coord of the pivot
	* @param w : width of the ellipse
	* @param w : height of the ellipse
	*/
	public void rotate(double angle, double x, double y, int w, int h) {
		this.setRotate(angle);

		double angle_rad = Math.toRadians(angle);
		angle_rad -= Math.PI/2.0;
		this.setX(x + Math.cos(angle_rad) * w/2);
		this.setY(y + Math.sin(angle_rad) * h/2);
	}
	public void rotate(double angle) { this.rotate(angle, this.pos.getX(), this.pos.getY(), this.width, this.height); }

	public double getAngle() { return this.angle; }

	public double computeAngle(double x, double y) {
		// double dot = (this.getX() * x) + (this.getY() * y);
		// double det = (this.getX() * y) - (this.getY() * x);
		// double angle = Math.atan2(det, dot);
		// return (int) angle;

		// double dy = y - this.getY();
		// double dx = x - this.getX();
		// double theta = Math.atan(dy/dx);
		// theta *= 180/Math.PI;
		// return (int) theta;

		// double angle = Math.toDegrees(Math.atan2(x - this.getX(), y - this.getY()));
		// if (angle < 0) angle += 360;
		// return (int) angle;
		double theta = Math.atan2(y - this.pos.getY(), x - this.pos.getX());

		// rotate the theta angle clockwise by 90 degrees (this makes 0 point NORTH)
		// NOTE: adding to an angle rotates it clockwise.
		// subtracting would rotate it counter-clockwise
		theta += Math.PI/2.0;

		// convert from radians to degrees
		// this will give you an angle from [0->270],[-180,0]
		double angle = Math.toDegrees(theta);

		// convert to positive range [0-360)
		// since we want to prevent negative angles, adjust them now.
		// we can assume that atan2 will not return a negative value
		// greater than one partial rotation
		if (angle < 0) angle += 360;

		return angle;
	}


	public boolean isIn(double sx, double sy, int ssize) {
		if (sx - ssize/2 > this.base.getX() + this.width || sx + ssize/2 < this.base.getX())
			return false;
		if (sy - ssize/2 > this.base.getY() + this.height || sy + ssize/2 < this.base.getY())
			return false;
		return true;
	}

	public double distance(Sprite s) {
		Position s_pos = s.getPosition();
		double tmpX = Math.pow((s_pos.getX() - this.pos.getX()), 2);
		double tmpY = Math.pow((s_pos.getY() - this.pos.getY()), 2);
		return Math.sqrt(tmpX + tmpY);
	}

	public double distance(double x, double y) {
		double tmpX = Math.pow((x - this.pos.getX()), 2);
		double tmpY = Math.pow((y - this.pos.getY()), 2);
		return Math.sqrt(tmpX + tmpY);
	}

}
