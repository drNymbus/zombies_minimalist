package model.drawable;

import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Sprite {
	private Pane layer;
	private int x, y;
	private int width, height;
	private Rectangle base;
	private int angle;
	private Color color;

	public Sprite(Pane layer, Color c, int x, int y, int w, int h) {
		this.x = x; this.y = x;
		this.width = w; this.height = h;

		this.base = new Rectangle(x - w/2, y - h/2, w, h);
		this.base.setFill(c);
		this.angle = 0;
		this.color = c;

		this.layer = layer;
		this.addToLayer();
	}

	public int getX() { return this.x; }
	public void setX(int x) { this.x = x; this.base.setX(x-this.width/2); }

	public int getY() { return this.y; }
	public void setY(int y) { this.y = y; this.base.setY(y-this.height/2); }

	public Color getColor() { return this.color; }
	public void setColor(Color c) { this.color = c; this.base.setFill(c); }

	public Pane getLayer() { return this.layer; }
	public Rectangle getShape() { return this.base; }
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }

	public void addToLayer() { this.layer.getChildren().add(this.base); }
	public void removeFromLayer() { this.layer.getChildren().remove(this.base); }

	public boolean isIn(Sprite s) {
		if (s.getX() - s.getWidth()/2 > base.getX() + this.width || s.getX() + s.getWidth()/2 < base.getX())
			return false;
		if (s.getY() - s.getHeight()/2 > base.getY() + this.height || s.getY() + s.getHeight()/2 < base.getY())
			return false;
		return true;
	}

	/*
	* @brief : applies a rotation from the base center
	* @param angle : [0-360) in degrees
	*/
	public void setRotate(int angle) {
		this.angle = angle; this.base.setRotate(angle);
	}

	/*
	* @brief : applies a rotation from a point at coord (x,y)
	* @param angle : [0-360) in degrees
	* @param x : the x coord of the pivot
	* @param y : the x coord of the pivot
	*/
	public void rotate(int angle, int x, int y) {
		this.setRotate(angle);
		this.setX(x + ((int) Math.cos(angle)));
		this.setY(y + ((int) Math.sin(angle)));
		// Rotate rotate = new Rotate();
		// rotate.setPivotX(x);
		// rotate.setPivotY(y);
		// rotate.setAngle(angle - this.angle); // rotate from new angel to old angle
		// this.base.getTransforms().addAll(rotate);
		// this.angle = angle;
	}
	public void rotate(int angle) { this.rotate(angle, this.x, this.y); }

	public int getAngle() { return this.angle; }

	public int computeAngle(int x, int y) {
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
		double theta = Math.atan2(y - this.getY(), x - this.getX());

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

		return (int) angle;
	}


	public boolean isIn(int sx, int sy, int ssize) {
		if (sx - ssize/2 > base.getX() + this.width || sx + ssize/2 < base.getX())
			return false;
		if (sy - ssize/2 > base.getY() + this.height || sy + ssize/2 < base.getY())
			return false;
		return true;
	}

	public double distance(Sprite s) {
		double tmpX = Math.pow((s.getX() - this.getX()), 2);
		double tmpY = Math.pow((s.getY() - this.getY()), 2);
		return Math.sqrt(tmpX + tmpY);
	}

	public double distance(int x, int y) {
		double tmpX = Math.pow((x - this.getX()), 2);
		double tmpY = Math.pow((y - this.getY()), 2);
		return Math.sqrt(tmpX + tmpY);
	}

}
