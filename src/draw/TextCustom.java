package draw;

import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class TextCustom {
	private Text text;
	private double width, height;

	public TextCustom(String s) {
		this.text = new Text(s);

		this.width = text.getLayoutBounds().getWidth();
		this.height = text.getLayoutBounds().getHeight();
	}

	public TextCustom(String s, Font f) {
		this.text = new Text(s);
		this.text.setFont(f);

		this.width = text.getLayoutBounds().getWidth();
		this.height = text.getLayoutBounds().getHeight();
	}

	public void setText(String s) {
		this.text.setText(s);
		this.width = text.getLayoutBounds().getWidth();
		this.height = text.getLayoutBounds().getHeight();
	}

	public Text getText() { return this.text; }

	public double getWidth() { return this.width; }
	public double getHeight() { return this.height; }

	public void setFont(Font f) {
		this.text.setFont(f);
	}

	public void setPosition(double x, double y) {
		this.text.setX(x);
		this.text.setY(y);
	}
	public void setPosition(Position p) { this.setPosition(p.getX(), p.getY()); }

	public void setX(double x) { this.text.setX(x); }
	public void setY(double y) { this.text.setY(y); }

}
