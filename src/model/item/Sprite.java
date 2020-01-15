package model.item;

import java.util.ArrayList;

import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

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

	public void setRotate(int angle) {
		this.angle = angle; this.base.setRotate(angle);
	}

	public void rotate(int angle, int x, int y) {
        Rotate rotate = new Rotate();
		rotate.setPivotX(x);
        rotate.setPivotY(y);
		rotate.setAngle(angle - this.angle);

        this.base.getTransforms().addAll(rotate);
		this.angle = angle;
	}
	public void rotate(int angle) { this.rotate(angle, this.x, this.y); }

	public int getAngle() { return this.angle; }

	public int computeAngle(int x, int y) {
		double dy = y - this.getY();
		double dx = x - this.getX();
		double theta = Math.atan(dy/dx);
		theta *= 180/Math.PI;
		return (int) theta;
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
